package com.tuten.challenge.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetTime;

@Getter
@Setter
public class GetTimeResponse {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss.SSSZ")
    private OffsetTime time;
    private String timezone;
}
