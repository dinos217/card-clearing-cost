package com.utils.card_clearing_cost.converters;

import com.utils.card_clearing_cost.dtos.UserAccountDto;
import com.utils.card_clearing_cost.entities.UserAccount;

public class UserAccountConverter {

    public static UserAccountDto convert(UserAccount userAccount) {
        return new UserAccountDto(userAccount.getId(), userAccount.getEmail(), userAccount.getPassword());
    }

    public static UserAccount convert(UserAccountDto userAccountDto) {
        return new UserAccount(userAccountDto.email(), userAccountDto.password());
    }
}
