package com.utils.card_clearing_cost.repositories;

import com.utils.card_clearing_cost.entities.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

    boolean existsByEmail(String email);
    Optional<UserAccount> findByEmail(String email);
}
