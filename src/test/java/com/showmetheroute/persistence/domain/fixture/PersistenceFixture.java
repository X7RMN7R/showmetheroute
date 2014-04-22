package com.showmetheroute.persistence.domain.fixture;

import com.showmetheroute.persistence.domain.WayPoint;

public class PersistenceFixture {
  public static WayPoint standardWayPoint() {
    WayPoint wayPoint = new WayPoint();
    wayPoint.setId("Nantes");
    wayPoint.setLatitude(47.21806);
    wayPoint.setLongitude(-1.55278);
    return wayPoint;
  }
}
