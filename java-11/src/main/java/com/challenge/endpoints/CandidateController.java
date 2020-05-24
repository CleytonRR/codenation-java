package com.challenge.endpoints;

import com.challenge.dto.CandidateDTO;
import com.challenge.mappers.CandidateMapper;
import com.challenge.service.impl.CandidateService;
import com.challenge.service.interfaces.CandidateServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @Autowired(required = false)
    private CandidateMapper candidateMapper;

    @GetMapping("/{userId}/{accelerationId}/{companyId}")
    public ResponseEntity<CandidateDTO> findById(@PathVariable Long userId,
                                                 @PathVariable Long accelerationId,
                                                 @PathVariable Long companyId) {
        CandidateDTO candidateDTO = this.candidateMapper
                .map(candidateService.findById(userId, companyId, accelerationId).get());
        if (candidateDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(candidateDTO);
    }

    @GetMapping
    public ResponseEntity<List<CandidateDTO>> findByCompanyIdOrAccelerationId(
            @RequestParam(required = false) Long companyId,
            @RequestParam(required = false) Long accelerationId) {
            if (companyId != null) {
                return ResponseEntity.ok(candidateMapper.map(candidateService.findByCompanyId(companyId)));
            }
            return ResponseEntity.ok(candidateMapper.map(candidateService.findByAccelerationId(accelerationId)));
    }
}
