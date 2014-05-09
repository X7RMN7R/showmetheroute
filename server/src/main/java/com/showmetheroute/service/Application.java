package com.showmetheroute.service;

import com.showmetheroute.config.MongoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;

import java.net.UnknownHostException;

@ComponentScan
@EnableAutoConfiguration
@Import(MongoConfiguration.class)
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
