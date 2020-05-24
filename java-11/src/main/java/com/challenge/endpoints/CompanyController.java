package com.challenge.endpoints;

import com.challenge.entity.Company;
import com.challenge.service.impl.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @GetMapping("/{id}")
    public ResponseEntity<Company> findById(@PathVariable("id") Long id) {
        Company company = companyService.findById(id).get();
        if (company == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(company);
    }

    @GetMapping
    public ResponseEntity<List<Company>> findByAccelerationOrUserId(
            @RequestParam(required = false) Long accelerationId,
            @RequestParam(required = false) Long userId) {
        if (accelerationId != null ) {
            return ResponseEntity.ok(companyService.findByAccelerationId(accelerationId));
        }
        return ResponseEntity.ok(companyService.findByUserId(userId));
    }
}
