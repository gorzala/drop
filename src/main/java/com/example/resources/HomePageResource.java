package com.example.resources;

import com.example.auth.AccessTokenPrincipal;
import io.dropwizard.auth.Auth;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/")
public class HomePageResource {
    @GET
    public String handleGetRequests(@Auth AccessTokenPrincipal accessTokenPrincipal) {
        return "Hello from Dropwizard!" + accessTokenPrincipal.getName();
    }
}
