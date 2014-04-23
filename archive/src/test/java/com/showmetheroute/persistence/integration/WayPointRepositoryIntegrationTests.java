package com.showmetheroute.persistence.integration;

import com.showmetheroute.config.MongoConfiguration;
import com.showmetheroute.persistence.repository.WayPointRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.showmetheroute.persistence.domain.fixture.PersistenceFixture.standardWayPoint;
import static junit.framework.TestCase.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MongoConfiguration.class})
public class WayPointRepositoryIntegrationTests {
  @Autowired
  WayPointRepository wayPointRepository;

  @Autowired
  MongoOperations mongo;

  @Before
  public void setup() throws Exception {
    mongo.dropCollection("waypoint");
  }

  @After
  public void teardown() {
    mongo.dropCollection("waypoint");
  }

  @Test
  public void thatPointIsInsertedIntoRepoWorks() throws Exception {
    assertEquals(0, mongo.getCollection("waypoint").count());
    wayPointRepository.save(standardWayPoint());
    assertEquals(1, mongo.getCollection("waypoint").count());
  }
}
