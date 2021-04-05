package com.tuten.challenge.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
public class GetTimeRequest {
    private String time;
    private String timezone;
}
