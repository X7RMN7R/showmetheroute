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
  
  public static WayPoint specialWayPoint() {
    WayPoint wayPoint = new WayPoint();
    wayPoint.setId("Special waypoint");
    wayPoint.setLatitude(48.390834);
    wayPoint.setLongitude(-4.485556);
    return wayPoint;
  }

  public static Track standardTrack() {
    Track track = new Track();
    track.setId("First track");
    track.getWayPoints().add(standardWayPoint());
    track.getWayPoints().add(specialWayPoint());
    return track;
  }
  
  public static String standardTrackJSON() {
    return "{ \"id\" : \"First_track\", "
        + "\"wayPoints\" : [ { \"id\" : \"Nantes\", \"latitude\" : "
        + "47.21806, \"longitude\" : -1.55278 }, { \"id\" : "
        + "\"Special_waypoint\", \"latitude\" : 48.390834, \"longitude\" : "
        + "-4.485556 } ] }";
  }
  
  public static String standardWaypointsJSON() {
    return "[ { \"id\" : \"Nantes\", \"latitude\" : "
        + "42.21806, \"longitude\" : -2.55278 }, { \"id\" : "
        + "\"Special_waypoint\", \"latitude\" : 42.390834, \"longitude\" : "
        + "-2.485556 } ]";
  }
  
  public static Track otherTrack() {
    Track track = new Track();
    track.setId("Other track");
    track.getWayPoints().add(specialWayPoint());
    return track;
  }
}
