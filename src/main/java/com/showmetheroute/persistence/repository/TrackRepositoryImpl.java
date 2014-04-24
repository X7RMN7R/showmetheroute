package com.showmetheroute.persistence.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapreduce.MapReduceResults;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class TrackRepositoryImpl implements AnalyseWayPoints {
  @Autowired
  private MongoTemplate mongoTemplate;

  @Override
  public Map<String, Integer> analyseWayPointsByLatitude() {

  }

}
