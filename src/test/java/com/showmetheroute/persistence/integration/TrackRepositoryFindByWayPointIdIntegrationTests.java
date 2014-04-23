package com.showmetheroute.persistence.integration;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.showmetheroute.config.MongoConfiguration;
import com.showmetheroute.persistence.domain.Track;
import com.showmetheroute.persistence.repository.TrackRepository;

import static com.showmetheroute.persistence.domain.fixture.PersistenceFixture.otherTrack;
import static com.showmetheroute.persistence.domain.fixture.PersistenceFixture.standardTrack;
import static junit.framework.TestCase.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MongoConfiguration.class})
public class TrackRepositoryFindByWayPointIdIntegrationTests {
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
  public void thatWayPointIsInsertedIntoRepoWorks() throws Exception {

    trackRepository.save(standardTrack());
    trackRepository.save(standardTrack());
    trackRepository.save(otherTrack());

    List<Track> trackWithNantes = trackRepository.findByWayPointsIdIn("Nantes");
    List<Track> trackWithSpecialWayPoint = trackRepository.findByWayPointsIdIn("Special waypoint");

    assertEquals(1, trackWithNantes.size());
    assertEquals(2, trackWithSpecialWayPoint.size());
  }
}
