package com.example;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;

public class DemoConfiguration extends Configuration {
    public OktaOAuthConfig oktaOAuth = new OktaOAuthConfig();
}
