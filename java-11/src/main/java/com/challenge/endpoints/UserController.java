package com.challenge.endpoints;

import com.challenge.entity.User;
import com.challenge.exceptions.ResourceNotFundException;
import com.challenge.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping()
    public ResponseEntity<List<User>> findByParam(@PathVariable(required = false) String nome,
                                                  @PathVariable(required = false) Long companyId) {
        if (nome != null) {
            return new ResponseEntity<>(this.userService.findByAccelerationName(nome), HttpStatus.OK);
        }

        if (companyId == null) {
            return new ResponseEntity<>(this.userService.findByCompanyId(companyId), HttpStatus.OK);
        }

        return new ResponseEntity<>(Collections.emptyList(), HttpStatus.BAD_REQUEST);
    }
}
