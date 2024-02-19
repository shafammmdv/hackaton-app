package com.app.service.impl;

import static com.app.mapper.TransactionMapper.TRANSACTION_MAPPER_INSTANCE;
import static com.app.util.Constant.ACCOUNT_NOT_FOUND_MSG;
import static com.app.util.Constant.BONUS_CARD_NAME;
import static com.app.util.Constant.CATEGORY_NOT_FOUND_MSG;
import static com.app.util.Constant.DIGITAL_CARD_NAME;
import static com.app.util.Constant.USER_NOT_FOUND_MSG;
import static java.lang.String.format;

import com.app.entity.Account;
import com.app.entity.Card;
import com.app.entity.Category;
import com.app.entity.Transaction;
import com.app.entity.User;
import com.app.exception.DataNotFoundException;
import com.app.model.request.TransactionRqModel;
import com.app.model.response.TransactionRsModel;
import com.app.repository.AccountRepository;
import com.app.repository.CardRepository;
import com.app.repository.CategoryRepository;
import com.app.repository.TransactionRepository;
import com.app.repository.UserRepository;
import com.app.service.TransactionService;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@AllArgsConstructor
@Service
public class TransactionServiceImpl implements TransactionService {
    private final UserRepository userRepo;
    private final CategoryRepository categoryRepo;
    private final AccountRepository accountRepo;
    private final TransactionRepository transactionRepo;
    private final CardRepository cardRepo;

    @Transactional
    @Override
    public TransactionRsModel createTransaction(TransactionRqModel request) {
        User user = findUserByUsername(request.getPhoneNumber());
        Account account = findAccountByUser(user);
        List<Card> cards = cardRepo.findAllByAccount(account);
        Category category = findCategoryByMcc(request.getMcc());

        Card digitalCard = cards.stream().filter(c -> c.getName().equals(DIGITAL_CARD_NAME))
                        .findFirst().get();

        Card bonusCard = cards.stream().filter(c -> c.getName().equals(BONUS_CARD_NAME))
                        .findFirst().get();

        digitalCard.setBalance(digitalCard.getBalance().subtract(request.getAmount()));

        if (category.equals(user.getPrimaryCategory())) {
            BigDecimal bonus = request.getAmount().multiply(BigDecimal.valueOf(300));
            bonusCard.setBalance(bonusCard.getBalance().add(bonus));
        } else if (category.equals(user.getSecondaryCategory())) {
            BigDecimal bonus = request.getAmount().multiply(BigDecimal.valueOf(200));
            bonusCard.setBalance(bonusCard.getBalance().add(bonus));
        } else {
            bonusCard.setBalance(bonusCard.getBalance().add(request.getAmount().multiply(BigDecimal.valueOf(100))));
        }
        cardRepo.save(digitalCard);
        cardRepo.save(bonusCard);

        Transaction transaction = transactionRepo.save(buildTransaction(request, user, account, category));

        return TRANSACTION_MAPPER_INSTANCE.buildGenericResponseModel(transaction);

    }

    private User findUserByUsername(String username) {
        return userRepo.findByPhoneNumber(username)
                .orElseThrow(() -> new DataNotFoundException(format(USER_NOT_FOUND_MSG, username), 1007));
    }

    private Category findCategoryByMcc(String mcc) {
        return categoryRepo.findCategoryByMcc(mcc)
                .orElseThrow(() -> new DataNotFoundException(format(CATEGORY_NOT_FOUND_MSG, mcc), 1010));
    }

    private Transaction buildTransaction(TransactionRqModel request, User user, Account account,
                                        Category category) {
        return Transaction.builder()
                .transactionId(UUID.randomUUID().toString())
                .dateTime(LocalDateTime.now())
                .amount(request.getAmount())
                .account(account)
                .type(request.getType())
                .category(category)
                .user(user)
                .build();
    }

    private Account findAccountByUser(User user) {
        return accountRepo.findAccountByUser(user)
                .orElseThrow(() -> new DataNotFoundException(format(ACCOUNT_NOT_FOUND_MSG, user.getUserId()), 1008));
    }


}
