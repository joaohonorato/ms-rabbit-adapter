package br.com.portobello.digital.ebspartnerspurchaseorderadapter.service.eventshub.config;

import br.com.portobello.digital.ebspartnerspurchaseorderadapter.service.authentication.GatewayAuthenticationClient;
import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

public class SensediaAuthConfig {

    private final GatewayAuthenticationClient authentication;
    @Autowired
    private ConnectionProperties connectionProperties;


    public SensediaAuthConfig(GatewayAuthenticationClient authentication) {
        this.authentication = authentication;
    }

    @Bean
    public RequestInterceptor requestTokenBearerInterceptor() {
        return requestTemplate -> {
            requestTemplate.header("client_id", connectionProperties.getGatewayClientId());
            requestTemplate.header("access_token", authentication.getAccessToken());
        };
    }
}
