package com.challenge.endpoints;

import com.challenge.entity.User;
import com.challenge.exceptions.ResourceNotFundException;
import com.challenge.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<User>(this.userService.findById(id)
        .orElseThrow(() -> new ResourceNotFundException("User")), HttpStatus.OK );
    }

    @GetMapping
    public List<User> findByAccelerationNameOrCompanyId(
            @RequestParam(required = false) String accelerationName,
            @RequestParam(required = false) Long companyId) {
        if (accelerationName != null) {
            return this.userService.findByAccelerationName(accelerationName);
        } else if(companyId != null) {
            return this.userService.findByCompanyId(companyId);
        }
        return Collections.emptyList();
    }
}
