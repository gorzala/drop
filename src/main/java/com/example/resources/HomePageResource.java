package com.example.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/")
public class HomePageResource {
    @GET
    public String handleGetRequests() {
        return "Hello from Dropwizard!";
    }
}
