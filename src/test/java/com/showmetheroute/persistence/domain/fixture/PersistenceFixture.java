package com.showmetheroute.persistence.domain.fixture;

import com.showmetheroute.persistence.domain.WayPoint;
import com.showmetheroute.persistence.domain.Track;

public class PersistenceFixture {
  public static WayPoint standardWayPoint() {
    WayPoint wayPoint = new WayPoint();
    wayPoint.setId("Nantes");
    wayPoint.setLatitude(47.21806);
    wayPoint.setLongitude(-1.55278);
    return wayPoint;
  }

  public static Track standardTrack() {
    Track track = new Track();
    track.getWayPointList().add(standardWayPoint());
    return track;
  }
}
