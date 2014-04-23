package com.showmetheroute.persistence.repository;

import com.showmetheroute.persistence.domain.Track;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TrackRepository extends CrudRepository<Track, String> {
  public List<Track> findByWayPointsIdIn(String ... id);
}
