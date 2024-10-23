package com.utils.card_clearing_cost.services;

import com.utils.card_clearing_cost.entities.UserAccount;
import com.utils.card_clearing_cost.repositories.UserAccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AppUserDetailsService implements UserDetailsService {

    private final UserAccountRepository userAccountRepository;

    @Autowired
    public AppUserDetailsService(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserAccount user = userAccountRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User: " + email + " was not found."));

        return new User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }
}
