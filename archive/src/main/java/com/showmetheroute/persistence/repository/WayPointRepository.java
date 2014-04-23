package com.showmetheroute.persistence.repository;

import com.showmetheroute.persistence.domain.WayPoint;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WayPointRepository extends CrudRepository<WayPoint, String> {
  public List<WayPoint> findByIdIn(String ... id);
}
