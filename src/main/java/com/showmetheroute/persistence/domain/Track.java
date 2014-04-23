package com.showmetheroute.persistence.domain;

import com.showmetheroute.persistence.domain.WayPoint;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Track {
  @Id
  private String id;
  
  private List<WayPoint> wayPointList;

  public Track() {
    this.wayPointList = new ArrayList<WayPoint>();
  }

  public List<WayPoint> getWayPointList() {
    return wayPointList;
  }

  public void setWayPointList(List<WayPoint> wayPointList) {
    this.wayPointList = wayPointList;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }
}
