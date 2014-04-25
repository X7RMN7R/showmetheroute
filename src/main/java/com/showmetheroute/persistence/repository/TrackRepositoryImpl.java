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
  public Map<String, Integer> analyseWayPointsByPopularity() {
    MapReduceResults<WayPointAnalysis> results = mongoTemplate.mapReduce("track",
        "classpath:waypointsmap.js",
        "classpath:waypointsreduce.js",
        WayPointAnalysis.class);

    Map<String, Integer> analysis = new HashMap<String, Integer>();

    for(WayPointAnalysis wayPointAnalysis: results) {
      analysis.put(wayPointAnalysis.getId(), wayPointAnalysis.getValue());
    }

    return analysis;
  }
  
  private static class WayPointAnalysis {
    private String id;
    private int value;

    public void setId(String name) {
      this.id = name;
    }

    public void setValue(int count) {
      this.value = count;
    }

    public String getId() {
      return id;
    }

    public int getValue() {
      return value;
    }
  }

}
