package com.showmetheroute.persistence.integration;

import com.mongodb.Mongo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import static com.showmetheroute.persistence.domain.fixture.MongoAssertions.usingMongo;
import static com.showmetheroute.persistence.domain.fixture.PersistenceFixture.standardTrack;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class TrackMappingIntegrationTests {
  MongoOperations mongo;

  @Before
  public void setup() throws Exception {
    mongo = new MongoTemplate(new SimpleMongoDbFactory(new Mongo(), "showmetheroute"));
    mongo.dropCollection("track");
  }

  @After
  public void tearDown() {
    mongo.dropCollection("track");
  }

  @Test
  public void thatTrackIsInsertedIntoCollectionHasCorrectIndexes() throws Exception {
    mongo.insert(standardTrack());
    assertEquals(1, mongo.getCollection("track").count());
    assertTrue(usingMongo(mongo).collection("track").hasIndexOn("_id"));
  }
}
