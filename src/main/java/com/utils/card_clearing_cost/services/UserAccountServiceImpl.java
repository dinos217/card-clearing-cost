package com.utils.card_clearing_cost.services;

import com.utils.card_clearing_cost.converters.UserAccountConverter;
import com.utils.card_clearing_cost.dtos.UserAccountDto;
import com.utils.card_clearing_cost.entities.UserAccount;
import com.utils.card_clearing_cost.exceptions.AlreadyExistsException;
import com.utils.card_clearing_cost.repositories.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    private final UserAccountRepository userAccountRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserAccountServiceImpl(UserAccountRepository userAccountRepository, PasswordEncoder passwordEncoder) {
        this.userAccountRepository = userAccountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserAccountDto registerUser(UserAccountDto userAccountDto) {

        if (userAccountRepository.existsByEmail(userAccountDto.email())) {
            throw new AlreadyExistsException("User with email " + userAccountDto.email() + " already exists.");
        }

        UserAccount userAccount = new UserAccount();
        userAccount.setEmail(userAccountDto.email());
        userAccount.setPassword(passwordEncoder.encode(userAccount.getPassword()));

        return UserAccountConverter.convert(userAccount);
    }
}
