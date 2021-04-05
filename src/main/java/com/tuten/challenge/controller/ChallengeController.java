package com.tuten.challenge.controller;

import com.tuten.challenge.request.GetTimeRequest;
import com.tuten.challenge.response.GetTimeWrapper;
import com.tuten.challenge.service.ChallengeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/challenge")
public class ChallengeController {
    private ChallengeService service;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GetTimeWrapper> getTimeZone(@RequestBody GetTimeRequest request) {
        return new ResponseEntity<>(service.getTime(request), HttpStatus.OK);
    }

}
