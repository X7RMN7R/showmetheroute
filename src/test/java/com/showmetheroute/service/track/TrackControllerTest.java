package com.showmetheroute.service.track;

import com.showmetheroute.persistence.repository.TrackRepository;
import com.showmetheroute.service.track.TrackController;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
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
  public void testFindAllTracksIsNull() throws Exception {
    when(trackRepository.findAll()).thenReturn(null);

    mockMvc.perform(get("/tracks")).andExpect(status().isOk());
  }
}
