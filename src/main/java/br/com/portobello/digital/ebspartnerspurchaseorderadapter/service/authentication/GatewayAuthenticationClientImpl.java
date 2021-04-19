package br.com.portobello.digital.ebspartnerspurchaseorderadapter.service.authentication;

import br.com.portobello.digital.ebspartnerspurchaseorderadapter.domain.oauth.OAuthGrantCodeRequest;
import br.com.portobello.digital.ebspartnerspurchaseorderadapter.domain.oauth.OAuthGrantCodeResponse;
import br.com.portobello.digital.ebspartnerspurchaseorderadapter.domain.oauth.OAuthTokenRefreshRequest;
import br.com.portobello.digital.ebspartnerspurchaseorderadapter.domain.oauth.OAuthTokenRequest;
import br.com.portobello.digital.ebspartnerspurchaseorderadapter.domain.oauth.OAuthTokenResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.common.exceptions.UnauthorizedClientException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
public class GatewayAuthenticationClientImpl implements GatewayAuthenticationClient {
    private final RestTemplate restTemplate;
    private Logger logger = LoggerFactory.getLogger(GatewayAuthenticationClientImpl.class);
    private String accessToken;
    private String grantCode;
    private Boolean tryRefreshToken = false;
    private String refreshToken;
    private LocalDateTime accessTokenExpiration;
    @Autowired
    private OauthConfiguration oauthConfig;

    public GatewayAuthenticationClientImpl(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    @Override
    public String getAccessToken() {
        if ((this.accessToken == null || LocalDateTime.now().minusSeconds(30).isAfter(accessTokenExpiration)) && Boolean.FALSE.equals(tryRefreshToken)) {
            logger.info("Criando token de acesso do gateway ...");

            generateGrantCode();
            generateAccessToken();

            logger.info("Token expirará às [{}], accessToken: [{}]", accessTokenExpiration, accessToken);
            return accessToken;
        } else {
            if (!LocalDateTime.now().minusSeconds(30).isAfter(accessTokenExpiration))
                return accessToken;
            try {
                generateRefreshedToken();
                tryRefreshToken = true;
                return accessToken;
            } catch (UnauthorizedClientException ue) {
                tryRefreshToken = false;
                getAccessToken();
                return accessToken;
            }
        }
    }

    private void generateGrantCode() {

        OAuthGrantCodeRequest body = new OAuthGrantCodeRequest();
        body.setClientId(oauthConfig.getClientId());
        body.setRedirectUri(oauthConfig.getRedirectUrl());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<OAuthGrantCodeRequest> requestEntity = new HttpEntity<>(body, headers);

        OAuthGrantCodeResponse response = restTemplate.exchange(oauthConfig.getGrantCodeUrl(), HttpMethod.POST, requestEntity, OAuthGrantCodeResponse.class).getBody();

        this.grantCode = response.getRedirectUri().split("=")[1];
    }

    private void generateAccessToken() {

        OAuthTokenRequest body = new OAuthTokenRequest();
        body.setGrantType("authorization_code");
        body.setCode(grantCode);

        HttpHeaders headers = buildHeaders();

        HttpEntity<OAuthTokenRequest> requestEntity = new HttpEntity<>(body, headers);

        OAuthTokenResponse response = restTemplate.exchange(oauthConfig.getAccessTokenUrl(), HttpMethod.POST, requestEntity, OAuthTokenResponse.class).getBody();

        this.accessToken = response.getAccessToken();
        this.refreshToken = response.getRefreshToken();
        this.accessTokenExpiration = LocalDateTime.now().plusSeconds(response.getExpiresIn());
    }

    private void generateRefreshedToken() {

        OAuthTokenRefreshRequest body = new OAuthTokenRefreshRequest();
        body.setGrantType("refresh_token");
        body.setRefreshToken(refreshToken);

        HttpHeaders headers = buildHeaders();

        HttpEntity<OAuthTokenRefreshRequest> requestEntity = new HttpEntity<>(body, headers);

        OAuthTokenResponse response = restTemplate.exchange(oauthConfig.getAccessTokenUrl(), HttpMethod.POST, requestEntity, OAuthTokenResponse.class).getBody();

        this.accessToken = response.getAccessToken();
        this.refreshToken = response.getRefreshToken();
        this.accessTokenExpiration = LocalDateTime.now().plusSeconds(response.getExpiresIn());
    }

    private HttpHeaders buildHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth(oauthConfig.getClientId(), oauthConfig.getClientSecret());
        return headers;
    }
}
