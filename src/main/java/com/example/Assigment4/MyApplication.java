package com.example.Assigment4;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/")
public class MyApplication extends ResourceConfig {
    public MyApplication() {
        register(new MyApplicationBinder());
        packages("com.example.Assigment4.controllers");
    }
}