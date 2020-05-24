package com.challenge.endpoints;

import com.challenge.dto.SubmissionDTO;
import com.challenge.mappers.SubmissionMapper;
import com.challenge.service.impl.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/submission")
public class SubmissionController {

    @Autowired
    private SubmissionService submissionService;

    @Autowired(required = false)
    private SubmissionMapper submissionMapper;

    @GetMapping
    public List<SubmissionDTO> findByChallengeIdAndAccelerationId(
            @RequestParam(required = false) Long accelerationId, @RequestParam(required = false) Long challengeId){
        return submissionMapper.map(submissionService.findByChallengeIdAndAccelerationId(challengeId, accelerationId));
    }
}
