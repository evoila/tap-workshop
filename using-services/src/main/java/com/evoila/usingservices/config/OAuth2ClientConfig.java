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
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;

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
                //.registrationId(bindings[0].get("provider"))
                .registrationId("appsso")
                .clientId(bindings[0].get("client-id"))
                .clientSecret(bindings[0].get("client-secret"))
                .authorizationGrantType(new AuthorizationGrantType(bindings[0].get("authorization-grant-type")))
                .clientAuthenticationMethod(new ClientAuthenticationMethod(bindings[0].get("client-authentication-method")))
                .scope(bindings[0].get("scope"))
                .redirectUri("https://mf-mongodb-client-sso.dev-ns-03.tap.dieunkrauts.de/login/oauth2/code/appsso")
                .build();
    }
}
