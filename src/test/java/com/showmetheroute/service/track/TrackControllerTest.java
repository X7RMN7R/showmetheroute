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


}
