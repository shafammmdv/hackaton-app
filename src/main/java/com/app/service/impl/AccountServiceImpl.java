package com.app.service.impl;

import static com.app.mapper.AccountMapper.ACCOUNT_MAPPER_INSTANCE;
import static com.app.util.Constant.ACCOUNT_NAME;
import static com.app.util.Constant.ACCOUNT_NOT_FOUND_MSG;
import static com.app.util.Constant.BONUS_CARD_NAME;
import static com.app.util.Constant.COMMISSION_CARD_NAME;
import static com.app.util.Constant.CONVERSION_CASHBACK_TYPE;
import static com.app.util.Constant.CONVERSION_COMMISSION_TYPE;
import static com.app.util.Constant.CONVERSION_RESPONSE_MESSAGE;
import static com.app.util.Constant.DIGITAL_CARD_NAME;
import static com.app.util.Constant.USER_NOT_FOUND_MSG;
import static java.lang.String.format;

import com.app.entity.Account;
import com.app.entity.Card;
import com.app.entity.User;
import com.app.exception.DataNotFoundException;
import com.app.model.request.ConversionRqModel;
import com.app.model.response.AccountRsModel;
import com.app.model.response.ResponseModel;
import com.app.repository.AccountRepository;
import com.app.repository.CardRepository;
import com.app.repository.UserRepository;
import com.app.service.AccountService;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Log4j2
@AllArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepo;
    private final UserRepository userRepo;
    private final CardRepository cardRepo;

    @Override
    public void saveAccount(User user) {
        Account account = buildAccount(ACCOUNT_NAME, user);
        accountRepo.save(account);
        saveCard(account);
    }

    @Override
    public void saveCard(Account account) {
        Card digitalCard = buildCard(DIGITAL_CARD_NAME, account);
        cardRepo.save(digitalCard);

        Card commissionCard = buildCard(COMMISSION_CARD_NAME, account);
        cardRepo.save(commissionCard);

        Card bonusCard = buildCard(BONUS_CARD_NAME, account);
        cardRepo.save(bonusCard);
    }

    @Override
    public AccountRsModel getAccount(String phoneNumber) {
        User user = findUserByUsername(phoneNumber);
        Account account = findAccountByUser(user);
        return ACCOUNT_MAPPER_INSTANCE.buildAccountResponseModel(account);
    }

    @Transactional
    @Override
    public ResponseModel convertAmount(ConversionRqModel request) {
        User user = findUserByUsername(request.getPhoneNumber());
        Account account = findAccountByUser(user);

        List<Card> cards = cardRepo.findAllByAccount(account);

        Card digitalCard =
                cards.stream().filter(c -> c.getName().equals(DIGITAL_CARD_NAME))
                        .findFirst().get();

        Card commissionCard =
                cards.stream().filter(c -> c.getName().equals(COMMISSION_CARD_NAME))
                        .findFirst().get();

        Card bonusCard =
                cards.stream().filter(c -> c.getName().equals(BONUS_CARD_NAME))
                        .findFirst().get();

        if (request.getType().equalsIgnoreCase(CONVERSION_COMMISSION_TYPE)) {
            commissionCard.setBalance(commissionCard.getBalance().add(request.getAmount().divide(BigDecimal.valueOf(100))));
        } else if (request.getType().equalsIgnoreCase(CONVERSION_CASHBACK_TYPE)) {
            digitalCard.setBalance(digitalCard.getBalance().add(request.getAmount().multiply(BigDecimal.valueOf(0.008))));
        }
        bonusCard.setBalance(bonusCard.getBalance().subtract(request.getAmount()));

        cardRepo.save(digitalCard);
        cardRepo.save(commissionCard);
        cardRepo.save(bonusCard);

        return ResponseModel.builder()
                .status(HttpStatus.OK)
                .body(CONVERSION_RESPONSE_MESSAGE)
                .build();
    }

    private Account buildAccount(String name, User user) {
        return Account.builder()
                .accountId(UUID.randomUUID().toString())
                .name(name)
                .user(user)
                .build();
    }

    private Card buildCard(String name, Account account) {
        return Card.builder()
                .cardId(UUID.randomUUID().toString())
                .name(name)
                .account(account)
                .number(generateCardNumber())
                .balance(BigDecimal.ZERO)
                .build();
    }

    private String generateCardNumber() {
        long number = 5200000000000000L + (long) (Math.random() * 100000000000000L);
        return Long.toString(number);
    }

    private User findUserByUsername(String username) {
        return userRepo.findByPhoneNumber(username)
                .orElseThrow(() -> new DataNotFoundException(format(USER_NOT_FOUND_MSG, username), 1008));
    }

    private Account findAccountByUser(User user) {
        return accountRepo.findAccountByUser(user)
                .orElseThrow(() -> new DataNotFoundException(format(ACCOUNT_NOT_FOUND_MSG, user.getUserId()), 1008));
    }
}
