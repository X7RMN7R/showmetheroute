package com.showmetheroute.persistence.integration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MongoConfiguration.class})
public class TrackRepositoryAnalyseWayPointsIntegrationTests {
  @Autowired 
  TrackRepository trackRepository;

  @Autowired
  MongoOperations mongo;

  @Before
  public vois setup() throws Exception {
    mongo.dropCollection("track");
  }

  @After
  public void teardown() {
    mongo.dropCollection("track");
  }

  @Test
  public void thatWayPointsAnalysisWorks() throws Exception {
    
  }
}
