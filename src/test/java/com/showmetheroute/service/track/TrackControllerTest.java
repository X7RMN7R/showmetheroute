package com.showmetheroute.service.track;

import static com.showmetheroute.persistence.domain.fixture.PersistenceFixture.standardTrackJSON;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.showmetheroute.persistence.domain.Track;
import com.showmetheroute.persistence.repository.TrackRepository;
public class TrackControllerTest {
  @Mock
  private TrackRepository trackRepository;

  @InjectMocks
  private TrackController trackController;

  private MockMvc mockMvc;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(trackController).build();
  }

  @Test
  public void testFindAllTracks() throws Exception {
    when(trackRepository.findAll()).thenReturn(null);

    mockMvc.perform(get("/tracks")).andExpect(status().isOk());
  }
  
  @Test 
  public void testCreateTrack() throws Exception {
    when(trackRepository.save(any(Track.class))).thenReturn(new Track());
    
    mockMvc.perform(post("/tracks").content(standardTrackJSON())
                      .contentType(MediaType.APPLICATION_JSON)
                      .accept(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isCreated());
  }
  
  @Test 
  public void testTrackCreationFails() throws Exception {
    when(trackRepository.save(any(Track.class))).thenReturn(null);
    
    mockMvc.perform(post("/tracks").content(standardTrackJSON())
                      .contentType(MediaType.APPLICATION_JSON)
                      .accept(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isNotAcceptable());
  }
  
  @Test
  public void testFindOneTrack() throws Exception {
    when(trackRepository.findOne("TRACK_ID")).thenReturn(new Track());
    
    mockMvc.perform(get("/tracks/{id}", "TRACK_ID")).andExpect(status().isOk());
  }
  
  @Test
  public void testTrackNotFound() throws Exception {
    when(trackRepository.findOne("TRACK_ID")).thenReturn(null);
    
    mockMvc.perform(get("/tracks/{id}", "TRACK_ID")).andExpect(status().isNotFound());
  }
  
  @Test
  public void testDeleteTrack() throws Exception {
    when(trackRepository.findOne("TRACK_ID")).thenReturn(new Track());
    
    mockMvc.perform(delete("/tracks/{id}", "TRACK_ID")).andExpect(status().isOk());
  }
  
  @Test
  public void testDeleteUnfoundTrack() throws Exception {
    when(trackRepository.findOne("TRACK_ID")).thenReturn(null);
    
    mockMvc.perform(delete("/tracks/TRACK_ID")).andExpect(status().isNotFound());
  }
  
  @Test
  public void testGetTrackWaypoints() throws Exception {
    when(trackRepository.findOne("TRACK_ID")).thenReturn(new Track());
    
    mockMvc.perform(get("/tracks/{id}/waypoints", "TRACK_ID")).andExpect(status().isOk());
  }
  
  @Test
  public void testGetUnfoundTrackWaypoints() throws Exception {
    when(trackRepository.findOne("TRACK_ID")).thenReturn(null);
    
    mockMvc.perform(get("/tracks/{id}/waypoints", "TRACK_ID")).andExpect(status().isNotFound());
  }
  
  @Test
  public void testUpdateTrackWaypoints() throws Exception {
    when (trackRepository.findOne("TRACK_ID")).thenReturn(new Track());
    when(trackRepository.save(any(Track.class))).thenReturn(new Track());
    
    mockMvc.perform(put("/tracks/{id}/waypoints", "TRACK_ID")).andExpect(status().isCreated());
  }
  
  @Test
  public void testUpdateUnfoundTrackWaypoints() throws Exception {
    when (trackRepository.findOne("TRACK_ID")).thenReturn(null);
    
    mockMvc.perform(put("/tracks/{id}/waypoints", "TRACK_ID")).andExpect(status().isNotFound());
  }
  
  @Test
  public void testTrackWaypointsUpdateFails() throws Exception {
    when (trackRepository.findOne("TRACK_ID")).thenReturn(null);
    when(trackRepository.save(any(Track.class))).thenReturn(null);
    
    mockMvc.perform(put("/tracks/{id}/waypoints", "TRACK_ID")).andExpect(status().isNotAcceptable());
  }
}
