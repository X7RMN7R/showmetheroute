package com.showmetheroute.persistence.integration;

import com.showmetheroute.config.MongoConfiguration;
import com.showmetheroute.persistence.repository.TrackRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.showmetheroute.persistence.domain.fixture.PersistenceFixture.standardTrack;
import static junit.framework.TestCase.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MongoConfiguration.class})
public class TrackRepositoryIntegrationTests {
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
  public void thatTrackIsInsertedIntoRepoWorks() throws Exception {
    assertEquals(0, mongo.getCollection("track").count());
    trackRepository.save(standardTrack());
    assertEquals(1, mongo.getCollection("track").count());
  }
}
