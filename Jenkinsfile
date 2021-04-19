@Library(['jenkins-shared@master']) _
pipeline{
    agent any
    options{
        disableConcurrentBuilds()
    }
    environment{
        JAVA_TOOL_OPTIONS = '-Duser.home=/var/maven'

        // Helm variables
        HELM_REPO_URL = "https://bitbucket.org/pbgrupo/helm.git"
        HELM_REPO_NAME = "helm"
        HELM_REPO_BRANCH = "master"
        HELM_VALUES_FILENAME_DEV = "values-develop.yaml"
        HELM_VALUES_FILENAME_PROD = "values-prod.yaml"

        VERSION = readMavenPom().getVersion()
        DEPLOYMENT_NAME = readMavenPom().getArtifactId()
        IMAGE_NAME = readMavenPom().getArtifactId()

        MAVEN_BUILD_IMAGE_DOCKER = "maven:3.6.3-jdk-11-slim"

        GIT_CREDENTIALS = "bitbucket-user-devops"

        KUBE_CREDENTIALS_DEV = "kubecfg-dev-portobello"
        KUBE_CREDENTIALS_PROD = "kubecfg-prod-portobello"

        DEVIMAGE_TAG = "dev"
        REGISTRY_URL = "docker.io"
        REGISTRY_IMAGE = "pbgrupo"
        DOCKER_REGISTRY_CREDENTIALS = "docker-hub-portobello"

        SETTINGS_NEXUS = credentials('nexus-settings-developer')
    }
    triggers {
        GenericTrigger(
            genericVariables: [
                [key: 'ref', value: '$.push.changes[0].new.name'],
                [key: 'before', value: '$.push.changes[0].old.target.hash'],
                [key: 'after', value: '$.push.changes[0].new.target.hash'],
                [key: 'name', value: '$.repository.name']
            ],
            token: env.JOB_NAME.tokenize('/')[0],
            printContributedVariables: true,
            printPostContent: true,
            silentResponse: false,

            regexpFilterText: '$name/$ref',
            regexpFilterExpression: env.JOB_NAME.tokenize('/')[0] + '/' + env.JOB_NAME.tokenize('/')[1]
        )
    }
    stages{
        stage("Init"){
            steps{
                script{
                    NEW_VERSION = getNewVersion(VERSION)
                    checkIfTagExists(NEW_VERSION)
                }
            }
        }

        stage("Test"){
            agent {
                docker {
                    image '${MAVEN_BUILD_IMAGE_DOCKER}'
                    args '--entrypoint="" -v $HOME:/var/maven'
                    reuseNode true
                }
            }
            steps{
                sh 'mvn clean test -s $SETTINGS_NEXUS -U'
            }
        }

        stage("SonarQube"){
            agent {
                docker {
                    image '${MAVEN_BUILD_IMAGE_DOCKER}'
                    args '--entrypoint="" -v $HOME:/var/maven'
                    reuseNode true
                }
            }
            steps{
                withSonarQubeEnv('SonarCloud') {
                    withEnv(["BRANCH_NAME=${env.BRANCH_NAME}"]){
                        script{
                            // A primeira execução do Sonarqube pode causar a quebra da pipeline, se não tiver um projeto no SonarQube configurado
                            // ele irá acusar um erro de que não existe uma branch principal, por isso é executado a avaliação sem uma branch específica,
                            // para que possa ser criado o projeto automaticamente no SonarQube.
                            // A mensagem apresentada no erro é "could not find a default branch to fall back on"
                            def logOutput = ''
                            logOutput = sh(returnStdout: true, script: 'mvn clean verify sonar:sonar -s $SETTINGS_NEXUS -Dsonar.branch.name=$BRANCH_NAME -DskipTests || true')
                            echo logOutput
                            if (logOutput.toLowerCase().contains("could not find a default branch to fall back on")){
                                sh 'mvn clean verify sonar:sonar -s $SETTINGS_NEXUS -DskipTests'
                            }
                        }
                    }
                }
            }
        }
        stage("OWASP"){
            agent {
                docker {
                    image '${MAVEN_BUILD_IMAGE_DOCKER}'
                    args '--entrypoint="" -v $HOME:/var/maven'
                    reuseNode true
                }
            }
            steps {
                sh 'mvn clean install org.owasp:dependency-check-maven:check -s $SETTINGS_NEXUS -Ddependency-check-format=XML -DskipTests'
            }
            post{
                success{
                    publishHTML(target: [
                        reportDir   : 'target',
                        reportFiles : 'dependency-check-report.html',
                        reportName  : 'Vulnerabilities',
                        allowMissing: true, keepAll: true])
                }
            }
        }

        stage("Quality Gate"){
            steps{
                timeout(time: 30, unit: 'MINUTES') {
                    // Parameter indicates whether to set pipeline to UNSTABLE if Quality Gate fails
                    // true = set pipeline to UNSTABLE, false = don't
                    // Requires SonarQube Scanner for Jenkins 2.7+
                    withSonarQubeEnv('SonarCloud') {
                        waitForQualityGate abortPipeline: true
                    }
                }
            }
        }

        stage("Maven Build Develop"){
            when{
                branch "develop"
            }
            agent {
                docker {
                    image '${MAVEN_BUILD_IMAGE_DOCKER}'
                    args '--entrypoint="" -v $HOME:/var/maven'
                    reuseNode true
                }
            }
            steps{
                sh 'mvn clean install -s $SETTINGS_NEXUS -DskipTests'
            }
        }

        stage("Docker Build Develop"){
            when{
                branch "develop"
            }
            steps{
                script{
                    env.DEV_TAG = "${DEVIMAGE_TAG}-${BUILD_NUMBER}"
                }
                sh "docker build . -t ${REGISTRY_IMAGE}/${IMAGE_NAME}:${DEV_TAG}"
            }
        }

        stage("Docker Push Develop"){
            when{
                branch "develop"
            }
            steps{
                withCredentials([usernamePassword(credentialsId: "${DOCKER_REGISTRY_CREDENTIALS}", usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]){
                    sh '''
                        set +x
                        echo ${PASSWORD} | docker login -u ${USERNAME} --password-stdin
                    '''
                    sh "docker push ${REGISTRY_IMAGE}/${IMAGE_NAME}:${DEV_TAG}"
                }
            }
            post{
                success{
                    sh "docker logout"
                    sh "docker image rm ${REGISTRY_IMAGE}/${IMAGE_NAME}:${DEV_TAG}"
                }
            }
        }

        stage("Deploy - Develop"){
            when{
                branch "develop"
            }
            steps{
                wrap([$class: 'org.jenkinsci.plugins.kubernetes.cli.KubectlBuildWrapper', credentialsId: "${KUBE_CREDENTIALS_DEV}"]) {
                    script{
                        helmDeploy(DEPLOYMENT_NAME, HELM_REPO_URL, HELM_REPO_NAME, HELM_REPO_BRANCH, DEV_TAG, HELM_VALUES_FILENAME_DEV, GIT_CREDENTIALS, env.WORKSPACE)
                    }
                }
            }
        }

        stage("Aprovar"){
            when{
                not{
                    branch "master"
                }
            }
            options{
                timeout(time: 5, unit: "MINUTES")
            }
            steps{
                input message: 'Enviar para PRODUÇÃO? (Clique em "Proceed" para continuar)'
            }
        }

        stage("Pom update version"){
            when{
                anyOf{
                    branch "develop"
                    branch "hotfix/*"
                }
            }
            agent {
                docker {
                    image '${MAVEN_BUILD_IMAGE_DOCKER}'
                    args '--entrypoint="" -v $HOME:/var/maven'
                    reuseNode true
                }
            }
            steps{
                sh "mvn -s $SETTINGS_NEXUS versions:set -DnewVersion=${NEW_VERSION} -DskipTests"
            }
        }

        stage("Git Merge to Master"){
            when{
                anyOf{
                    branch "develop"
                    branch "hotfix/*"
                }
            }
            steps{
                withCredentials([usernamePassword(credentialsId: "${GIT_CREDENTIALS}", usernameVariable: 'GIT_USERNAME', passwordVariable: 'GIT_PASSWORD')]){
                    withEnv(["BRANCH_NAME=${env.BRANCH_NAME}", "NEW_VERSION=${NEW_VERSION}"]){ 
                        sh '''
                            git config user.name 'Devops'
                            git config user.email 'devops@portobello.com.br'
                        '''
                        sh '''
                            git config --local credential.helper "!f() { echo username=\\$GIT_USERNAME; echo password=\\$GIT_PASSWORD; }; f"
                            git checkout $BRANCH_NAME
                            git add pom.xml
                            git commit -m 'Update pom.xml version to $NEW_VERSION'
                            
                            git checkout master

                            git merge -m 'Merge from $BRANCH_NAME branch by Jenkins Pipeline' $BRANCH_NAME --no-ff

                            git tag -a $NEW_VERSION -m 'Version $BRANCH_NAME $NEW_VERSION created'

                            git push origin $NEW_VERSION
                            git push origin master

                            git checkout $BRANCH_NAME
                            git pull origin master
                            git push origin $BRANCH_NAME
                        '''
                    }
                }
            }
        }

        stage("Maven Build Master"){
            when{
                branch "master"
            }
            agent {
                docker {
                    image '${MAVEN_BUILD_IMAGE_DOCKER}'
                    args '--entrypoint="" -v $HOME:/var/maven'
                    reuseNode true
                }
            }
            steps{
                sh 'mvn clean install -s $SETTINGS_NEXUS -DskipTests'
            }
        }

        stage("Docker Build Master"){
            when{
                branch "master"
            }
            steps{
                script{
                    env.PROD_TAG = "${VERSION}"
                }
                sh "docker build . -t ${REGISTRY_IMAGE}/${IMAGE_NAME}:${PROD_TAG}"
            }
        }

        stage("Docker Push Master"){
            when{
                branch "master"
            }
            steps{
                withCredentials([usernamePassword(credentialsId: "${DOCKER_REGISTRY_CREDENTIALS}", usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]){
                    sh '''
                        set +x
                        echo ${PASSWORD} | docker login -u ${USERNAME} --password-stdin
                    '''
                    sh "docker push ${REGISTRY_IMAGE}/${IMAGE_NAME}:${PROD_TAG}"
                }
            }
            post{
                success{
                    sh "docker logout"
                    sh "docker image rm ${REGISTRY_IMAGE}/${IMAGE_NAME}:${PROD_TAG}"
                }
            }
        }

        stage("Deploy - Prod"){
            when{
                branch "master"
            }
            steps{
                wrap([$class: 'org.jenkinsci.plugins.kubernetes.cli.KubectlBuildWrapper', credentialsId: "${KUBE_CREDENTIALS_PROD}"]) {
                    script{
                        helmDeploy(DEPLOYMENT_NAME, HELM_REPO_URL, HELM_REPO_NAME, HELM_REPO_BRANCH, PROD_TAG, HELM_VALUES_FILENAME_PROD, GIT_CREDENTIALS, env.WORKSPACE)
                    }
                }
            }
        }
    }
    post{
        always{
            deleteDir()
        }
    }
}