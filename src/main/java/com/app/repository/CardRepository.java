package com.app.repository;

import com.app.entity.Account;
import com.app.entity.Card;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findAllByAccount(Account account);
}
