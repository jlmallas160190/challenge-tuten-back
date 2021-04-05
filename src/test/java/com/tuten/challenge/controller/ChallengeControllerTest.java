package com.tuten.challenge.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tuten.challenge.request.GetTimeRequest;
import com.tuten.challenge.response.GetTimeResponse;
import com.tuten.challenge.response.GetTimeWrapper;
import com.tuten.challenge.service.ChallengeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChallengeControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    MockMvc mockMvc;
    @MockBean
    private ChallengeService service;

    private ObjectMapper objectMapper;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void whenGetTime_ThenReturnOk() throws Exception {
        GetTimeRequest request = new GetTimeRequest();
        request.setTime("10:21:00");
        request.setTimezone("+08:00");

        GetTimeWrapper wrapper = new GetTimeWrapper();
        GetTimeResponse response = new GetTimeResponse();
        response.setTimezone("utc");
        wrapper.setResponse(response);

        when(service.getTime(request)).thenReturn(wrapper);
        String requestString = objectMapper.writeValueAsString(request);
        this.mockMvc.perform(post("/challenge").content(requestString).contentType(MediaType.APPLICATION_JSON)).andDo(print()).
                andExpect(status().isOk());

        verify(service, times(1)).getTime(any());
    }

    @Test
    public void givenBadRequest_whenGetTime_ThenReturnStatus400() throws Exception {
        GetTimeRequest request = new GetTimeRequest();
        request.setTime("10:21:00");
        request.setTimezone("+08:00");
        GetTimeWrapper wrapper = new GetTimeWrapper();
        GetTimeResponse response = new GetTimeResponse();
        response.setTimezone("utc");
        wrapper.setResponse(response);

        when(service.getTime(request)).thenReturn(wrapper);
        this.mockMvc.perform(post("/challenge").contentType(MediaType.APPLICATION_JSON)).andDo(print()).
                andExpect(status().isBadRequest());

        verify(service, never()).getTime(request);
    }
}