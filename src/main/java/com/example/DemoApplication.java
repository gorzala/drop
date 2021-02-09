package com.example;

import com.example.auth.AccessTokenPrincipal;
import com.example.auth.OktaOAuthAuthenticator;
import com.example.resources.HomePageResource;
import com.okta.jwt.JwtHelper;
import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.oauth.OAuthCredentialAuthFilter;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.apache.commons.lang3.StringUtils;

public class DemoApplication extends Application<DemoConfiguration> {

    public static void main(final String[] args) throws Exception {
        new DemoApplication().run(args);
    }

    @Override
    public String getName() {
        return "demo";
    }

    @Override
    public void initialize(final Bootstrap<DemoConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final DemoConfiguration configuration,
                    final Environment environment) {
        environment.jersey().register(new HomePageResource());
        configureOAuth(configuration, environment);
    }

    private void configureOAuth(final DemoConfiguration configuration, final Environment environment) {
        try {
            OktaOAuthConfig widgetConfig = configuration.oktaOAuth;
            // Configure the JWT Validator, it will validate Okta's JWT access tokens
            JwtHelper helper = new JwtHelper()
                    .setIssuerUrl(widgetConfig.issuer)
                    .setClientId(widgetConfig.clientId);

            // set the audience only if set, otherwise the default is: api://default
            String audience = widgetConfig.audience;
            if (StringUtils.isNotEmpty(audience)) {
                helper.setAudience(audience);
            }

            // register the OktaOAuthAuthenticator
            environment.jersey().register(new AuthDynamicFeature(
                    new OAuthCredentialAuthFilter.Builder<AccessTokenPrincipal>()
                            .setAuthenticator(new OktaOAuthAuthenticator(helper.build()))
                            .setPrefix("Bearer")
                            .buildAuthFilter()));

            // Bind our custom principal to the @Auth annotation
            environment.jersey().register(new AuthValueFactoryProvider.Binder<>(AccessTokenPrincipal.class));
        } catch (Exception e) {
            throw new IllegalStateException("Failed to configure JwtVerifier", e);
        }
    }

}
