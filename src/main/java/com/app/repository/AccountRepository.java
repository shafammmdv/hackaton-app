package com.app.repository;

import com.app.entity.Account;
import com.app.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByAccountId(String accountId);

    Optional<Account> findAccountByUser(User user);

}
