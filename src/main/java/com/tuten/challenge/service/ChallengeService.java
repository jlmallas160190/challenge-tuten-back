package com.tuten.challenge.service;

import com.tuten.challenge.request.GetTimeRequest;
import com.tuten.challenge.response.GetTimeResponse;
import com.tuten.challenge.response.GetTimeWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.OffsetTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@Service
@Slf4j
public class ChallengeService {
    public GetTimeWrapper getTime(GetTimeRequest request) {
        try {
            GetTimeResponse getTimeResponse = new GetTimeResponse();
            OffsetTime offsetTime = OffsetTime.parse(String.format("%s%s", request.getTime(), request.getTimezone()), DateTimeFormatter.ISO_OFFSET_TIME);
            getTimeResponse.setTime(offsetTime.withOffsetSameInstant(ZoneOffset.UTC));
            getTimeResponse.setTimezone("UTC");
            GetTimeWrapper response = new GetTimeWrapper();
            response.setResponse(getTimeResponse);
            return response;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }

    }
}
