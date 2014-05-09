package com.showmetheroute.service.hello;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import static junit.framework.TestCase.assertEquals;

public class HelloServiceTests {
  @Test
  public void thatItSaysHello() {
    RestTemplate restTemplate = new RestTemplate();
    Map<String, String> urlVariables = new HashMap<String, String>();
    urlVariables.put("name", "Sandrine");
    Greeting greeting = restTemplate.getForObject("http://localhost:8080/greeting?name={name}", Greeting.class, urlVariables);
    assertEquals("Hello, Sandrine!", greeting.getContent());
  }
}
