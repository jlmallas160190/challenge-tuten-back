package com.tuten.challenge.service;

import com.tuten.challenge.request.GetTimeRequest;
import com.tuten.challenge.response.GetTimeWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class ChallengeServiceTest {
    @InjectMocks
    private ChallengeService service;

    @Test
    public void whenGetTime_ThenReturnOk() {
        GetTimeRequest request = new GetTimeRequest();
        request.setTime("10:21:00");
        request.setTimezone("+08:00");
        GetTimeWrapper wrapper = service.getTime(request);
        assertNotNull(wrapper);
        assertEquals(wrapper.getResponse().getTime().toString(),"02:21Z");
    }

    @Test(expected = RuntimeException.class)
    public void whenGetTime_ThenReturnException() {
        GetTimeRequest request = new GetTimeRequest();
        request.setTime("10:21:00");
        request.setTimezone("error");
        service.getTime(request);
    }
}