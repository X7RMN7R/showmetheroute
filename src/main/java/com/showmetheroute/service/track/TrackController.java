package com.showmetheroute.service.track;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.util.UriComponentsBuilder;

import com.showmetheroute.persistence.domain.Track;
import com.showmetheroute.persistence.domain.WayPoint;
import com.showmetheroute.persistence.repository.TrackRepository;

@Controller
public class TrackController {
  
  @Autowired
  TrackRepository trackRepository;
  
  @RequestMapping(method = RequestMethod.GET, value = "/tracks")
  @ResponseStatus(HttpStatus.OK)
  public @ResponseBody Iterable<Track> listTracks() {
	  return trackRepository.findAll();
  }
  
  @RequestMapping(method = RequestMethod.GET, value = "/tracks/{id}")
  public ResponseEntity<Track> getTrack(@PathVariable String id) {
    Track track = trackRepository.findOne(id);

    if (track == null) {
      return new ResponseEntity<Track>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<Track>(track, HttpStatus.OK);
  }
  
  @RequestMapping(method = RequestMethod.DELETE, value = "/tracks/{id}")
  public ResponseEntity<Track> deleteTrack(@PathVariable String id) {
    Track track = trackRepository.findOne(id);
    
    if (track == null) {
      return new ResponseEntity<Track>(HttpStatus.NOT_FOUND);
    }
    
    return new ResponseEntity<Track>(track, HttpStatus.OK);
  }
  
  @RequestMapping(method = RequestMethod.POST, value = "/tracks")
  public ResponseEntity<Track> createTrack(@RequestBody Track track, 
                                          UriComponentsBuilder builder) {
    Track createdTrack = trackRepository.save(track);
    
    if (createdTrack == null) {
      return new ResponseEntity<Track>(createdTrack, HttpStatus.NOT_ACCEPTABLE);
    }
    
    HttpHeaders headers = new HttpHeaders();
    headers.setLocation(
            builder.path("/tracks/{id}")
                    .buildAndExpand(createdTrack.getId()).toUri());

    return new ResponseEntity<Track>(createdTrack, headers, HttpStatus.CREATED);
  }
  
  @RequestMapping(method = RequestMethod.GET, value="/tracks/{id}/waypoints")
  public ResponseEntity<List<WayPoint>> getTrackWaypoints(@PathVariable String id) {
    Track track = trackRepository.findOne(id);
    
    if (track == null || track.getWayPoints() == null) {
      return new ResponseEntity<List<WayPoint>>(HttpStatus.NOT_FOUND);
    }
    
    return new ResponseEntity<List<WayPoint>>(track.getWayPoints(), HttpStatus.OK);
  }

  @RequestMapping(method = RequestMethod.PUT,  value="/tracks/{id}/waypoints")
  public ResponseEntity<List<WayPoint>> updateTrackWaypoints(@PathVariable String id, 
                                                            @RequestBody List<WayPoint> waypoints,
                                                            UriComponentsBuilder builder) {
    Track track = trackRepository.findOne(id);
    
    if (track == null || track.getWayPoints() == null) {
      return new ResponseEntity<List<WayPoint>>(HttpStatus.NOT_FOUND);
    }
    
    track.setWayPoints(waypoints);
    Track updatedTrack = trackRepository.save(track);
    
    if (updatedTrack == null) {
      return new ResponseEntity<List<WayPoint>>(waypoints, HttpStatus.NOT_ACCEPTABLE);
    }
    
    HttpHeaders headers = new HttpHeaders();
    headers.setLocation(
            builder.path("/tracks/{id}")
                    .buildAndExpand(updatedTrack.getId()).toUri());

    return new ResponseEntity<List<WayPoint>>(updatedTrack.getWayPoints(), headers, HttpStatus.CREATED);
  }
}
