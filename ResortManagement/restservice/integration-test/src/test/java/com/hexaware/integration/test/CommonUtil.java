package com.hexaware.integration.test;

import java.net.URI;
import java.net.URISyntaxException;

public class CommonUtil {
  public static final String host;
  public static final String port;
  public static final String webapp;
  public static final String uri_prefix;
  static {
      host = System.getProperty("service.host", "localhost");
      port = System.getProperty("service.port", "8080");
      webapp = System.getProperty("service.webapp", "ResortManagement");
      uri_prefix = "http://" + host + ":" + port + "/" + webapp;
  }
  public static URI getURI(String path) throws URISyntaxException {
      return new URI(uri_prefix + path);
  }
} 
    
