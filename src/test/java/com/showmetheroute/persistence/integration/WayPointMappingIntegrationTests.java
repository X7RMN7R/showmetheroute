package com.showmetheroute.persistence.integration;

import com.mongodb.Mongo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import static com.showmetheroute.persistence.domain.fixture.MongoAssertions.usingMongo;
import static com.showmetheroute.persistence.domain.fixture.PersistenceFixture.standardWayPoint;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class WayPointMappingIntegrationTests {
  MongoOperations mongo;

  @Before
  public void setup() throws Exception {
    mongo = new MongoTemplate(new SimpleMongoDbFactory(new Mongo(), "showmetheroute"));
    mongo.dropCollection("waypoint");
  }

  @After
  public void tearDown() {
    mongo.dropCollection("waypoint");
  }

  @Test
  public void thatPointIsInsertedIntoCollectionHasCorrectIndexes() throws Exception {
    mongo.insert(standardWayPoint());
    assertEquals(1, mongo.getCollection("waypoint").count());
    assertTrue(usingMongo(mongo).collection("waypoint").hasIndexOn("_id"));
    assertTrue(usingMongo(mongo).collection("waypoint").hasIndexOn("latitude"));
    assertTrue(usingMongo(mongo).collection("waypoint").hasIndexOn("longitude"));
  }

  @Test
  public void thatPointCustomMappingWorks() throws Exception {
    mongo.insert(standardWayPoint());
    assertTrue(usingMongo(mongo).collection("waypoint").first().hasField("latitude"));
  }
}
