package com.demo.controller;

import com.demo.json.LoginRequest;
import com.demo.json.LoginResponse;
import com.demo.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.demo.util.SecurityConstants.AUTH_URL;

@RestController
@RequestMapping(AUTH_URL)
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticate;

    @PostMapping
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginRequest loginRequest) {
        LoginResponse response = authenticate.authenticate(loginRequest);
        return ResponseEntity.ok(response);
    }
}
