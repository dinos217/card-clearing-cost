package com.utils.card_clearing_cost.controllers;

import com.utils.card_clearing_cost.dtos.UserAccountDto;
import com.utils.card_clearing_cost.services.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "v1/auth")
public class AuthController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserAccountDto userAccountDto) throws Exception {

        logger.info("Started authentication of user: " + userAccountDto.email());

        try {
            return ResponseEntity.status(HttpStatus.OK).body(authService.authenticate(userAccountDto));
        } catch (BadCredentialsException e) {
            logger.info("ERROR: Bad login attempt.");
            throw new BadCredentialsException("Invalid credentials.");
        }
    }
}
