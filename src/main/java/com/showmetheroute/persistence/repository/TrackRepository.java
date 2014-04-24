package com.showmetheroute.persistence.repository;

import com.showmetheroute.persistence.domain.Track;
import com.showmetheroute.persistence.repository.AnalyseWayPoints;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TrackRepository extends CrudRepository<Track, String> extends AnalyseWayPoints {
  public List<Track> findByWayPointsIdIn(String ... id);
}
