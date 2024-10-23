package com.utils.card_clearing_cost.services;

import com.utils.card_clearing_cost.dtos.UserAccountDto;

public interface AuthService {

    String authenticate(UserAccountDto userAccountDto);
}
