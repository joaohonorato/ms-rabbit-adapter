package br.com.portobello.digital.ebspartnerspurchaseorderadapter.service.authentication;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class OauthConfiguration {

    @Value("${app.eventsHub.gatewayOauthUrl}")
    private String oauthUrl;

    @Value("${app.eventsHub.gatewayGrantCodeUri}")
    private String grantCodeUri;

    @Value("${app.eventsHub.gatewayAccessTokenUri}")
    private String accessTokenUri;

    @Value("${app.eventsHub.gatewayClientId}")
    private String clientId;

    @Value("${app.eventsHub.gatewayClientSecret}")
    private String clientSecret;

    @Value("${app.eventsHub.gatewayOauthRedirectUrl}")
    private String redirectUrl;

    public String getGrantCodeUrl() {
        return oauthUrl + grantCodeUri;
    }

    public String getAccessTokenUrl() {
        return oauthUrl + accessTokenUri;
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

}
