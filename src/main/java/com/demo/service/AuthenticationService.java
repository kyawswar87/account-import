package com.demo.service;

import com.demo.filter.TokenProvider;
import com.demo.json.LoginRequest;
import com.demo.json.LoginResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthenticationService {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    TokenProvider tokenProvider;

    public LoginResponse authenticate(LoginRequest loginRequest) {
        log.info("authenticate by {}", loginRequest);
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUserName(),
                        loginRequest.getPassword()
                )
        );
        var user = userService.findOneByUserName(loginRequest.getUserName())
                .orElseThrow();

        String token = tokenProvider.createToken(user,false);
        return LoginResponse
                .builder()
                .token(token)
                .build();
    }
}
