package com.showmetheroute.service.track;

import com.showmetheroute.persistence.domain.Track;
import com.showmetheroute.persistence.repository.TrackRepository;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TrackController {
  private final AtomicLong counter = new AtomicLong();
  
  @Autowired
  TrackRepository trackRepository;
  
  @RequestMapping("/tracks")
  public @ResponseBody Iterable<Track> listTracks(
              /*@RequestParam(value="name", required=false, defaultValue="World") String name*/) {
	
	return trackRepository.findAll();
  }
}
