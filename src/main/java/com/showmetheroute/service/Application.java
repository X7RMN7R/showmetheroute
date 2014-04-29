package com.showmetheroute.service;

import com.mongodb.Mongo;
import com.showmetheroute.persistence.repository.TrackRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.net.UnknownHostException;

@Configuration
@EnableMongoRepositories(basePackages = "com.showmetheroute.persistence.repository",
    includeFilters = @ComponentScan.Filter(value = {TrackRepository.class}, type = FilterType.ASSIGNABLE_TYPE))
public class Application {

  public @Bean
  MongoTemplate mongoTemplate(Mongo mongo) throws UnknownHostException {
    return new MongoTemplate(mongo, "showmetheroute");
  }

  public @Bean Mongo mongo() throws UnknownHostException {
    return new Mongo("localhost");
  }
  
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
