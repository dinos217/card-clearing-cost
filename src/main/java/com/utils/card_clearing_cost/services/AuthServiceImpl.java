package com.utils.card_clearing_cost.services;

import com.utils.card_clearing_cost.dtos.UserAccountDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthServiceImpl(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public String authenticate(UserAccountDto userAccountDto) {

        Authentication authentication;
        authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userAccountDto.email(), userAccountDto.password()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        logger.info("SUCCESS: User '" + userAccountDto.email() + "' logged in successfully");

        return "Logged in successfully.";
    }
}
