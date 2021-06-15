package com.hexaware.resortmanagement.util;

import java.io.IOException;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

/**
 * this class is to accept cross-origin requests.
 */
class CORSFilter implements ContainerResponseFilter {
  /**
   * method to add the headers.
   * @param req for requesting the request object
   * @param res for responses
   */
  @Override
  public void filter(final ContainerRequestContext req, final ContainerResponseContext res) throws IOException {
    res.getHeaders().add("Access-Control-Allow-Origin", "*");
    res.getHeaders().add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
    res.getHeaders().add("Access-Control-Allow-Credentials", "true");
    res.getHeaders().add("Access-Control-Allow-Methods",
        "POST, GET, PUT, DELETE, HEAD, OPTIONS");
  }
}

/**
 * scan the REST classes and CORS filter.
 */
@ApplicationPath("/api")
public class RMApplication extends ResourceConfig {
  /**
   * constructor for RMApplication.
   */
  public RMApplication() {
    //register the package which hosts the REST classes
    //register the resources (POJO classes) and factory and DAO classes
    packages("com.hexaware.resortmanagement.util");
    register(new CORSFilter());

    //tracing the server properties
    property(ServerProperties.TRACING, "ALL");
  }
}
