package com.medilabo.front.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Bean
    public RestClient patientRestClient(@Value("${gateway.url}") String gatewayUrl,
                                        @Value("${gateway.username}") String username,
                                        @Value("${gateway.password}") String password) {

        return RestClient.builder()
                .baseUrl(gatewayUrl)
                .requestInterceptor(new BasicAuthenticationInterceptor(username, password))
                .build();
    }
}
