package com.challenge.endpoints;

import com.challenge.entity.Challenge;
import com.challenge.service.impl.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/challenge")
public class ChallengeController {

    @Autowired
    ChallengeService challengeService;

    @GetMapping
    public List<Challenge> indByAccelerationIdAndUserId(@RequestParam(required = false) Long accelerationId,
                                                        @RequestParam(required = false) Long userId) {
        return challengeService.findByAccelerationIdAndUserId(accelerationId, userId);
    }
}
