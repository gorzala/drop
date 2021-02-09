package com.example;

import com.example.resources.HomePageResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

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
    }

}
