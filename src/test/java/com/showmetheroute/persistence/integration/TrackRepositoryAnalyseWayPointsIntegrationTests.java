package com.showmetheroute.persistence.integration;

import static com.showmetheroute.persistence.domain.fixture.PersistenceFixture.otherTrack;
import static com.showmetheroute.persistence.domain.fixture.PersistenceFixture.standardTrack;
import static junit.framework.TestCase.assertEquals;

import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.showmetheroute.config.MongoConfiguration;
import com.showmetheroute.persistence.repository.TrackRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MongoConfiguration.class})
public class TrackRepositoryAnalyseWayPointsIntegrationTests {
  @Autowired 
  TrackRepository trackRepository;

  @Autowired
  MongoOperations mongo;

  @Before
  public void setup() throws Exception {
    mongo.dropCollection("track");
  }

  @After
  public void teardown() {
    mongo.dropCollection("track");
  }

  @Test
  public void thatWayPointsAnalysisWorks() throws Exception {
    trackRepository.save(standardTrack());
    trackRepository.save(standardTrack());
    trackRepository.save(standardTrack());
    trackRepository.save(otherTrack());
    trackRepository.save(otherTrack());

    Map<String, Integer> wayPoints = trackRepository.analyseWayPointsByPopularity();

    assertEquals(2, wayPoints.size());
    assertEquals(1, (int)wayPoints.get("Nantes"));
    assertEquals(2, (int)wayPoints.get("Special waypoint"));
  }
}
