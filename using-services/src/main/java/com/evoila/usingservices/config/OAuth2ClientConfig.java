package com.evoila.usingservices.config;

import com.nebhale.bindings.Binding;
import com.nebhale.bindings.Bindings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.ClientRegistrations;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;

@Configuration
@EnableWebSecurity
public class OAuth2ClientConfig {

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        return new InMemoryClientRegistrationRepository(getAppSsoClientRegistration());
    }

    private ClientRegistration getAppSsoClientRegistration() {
        Binding[] bindings = Bindings.fromServiceBindingRoot();
        bindings = Bindings.filter(bindings, "appsso");
        if (bindings.length != 1) {
            System.err.printf("Incorrect number of appsso drivers: %d\n", bindings.length);
            System.exit(1);
        }
        return ClientRegistrations.fromIssuerLocation(bindings[0].get("issuer-uri"))
                .registrationId(bindings[0].get("provider"))
                .clientId(bindings[0].get("client-id"))
                .clientSecret(bindings[0].get("client-secret"))
                .redirectUri("https://mf-mongodb-client-sso.dev-ns-03.tap.dieunkrauts.de/login/oauth2/code/appsso")
                .build();
    }
}
