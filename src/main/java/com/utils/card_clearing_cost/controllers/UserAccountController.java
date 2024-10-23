package com.utils.card_clearing_cost.controllers;

import com.utils.card_clearing_cost.dtos.UserAccountDto;
import com.utils.card_clearing_cost.services.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/user")
public class UserAccountController {

    private final UserAccountService userAccountService;

    @Autowired
    public UserAccountController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @PostMapping(value = "/register", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserAccountDto> register(@RequestBody UserAccountDto userAccountDto) {

        return ResponseEntity.status(HttpStatus.OK).body(userAccountService.registerUser(userAccountDto));
    }
}
