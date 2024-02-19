package com.app.service;

import com.app.entity.Account;
import com.app.entity.User;
import com.app.model.request.ConversionRqModel;
import com.app.model.response.AccountRsModel;

import com.app.model.response.ResponseModel;
import java.util.List;

public interface AccountService {
    void saveAccount(User user);

    void saveCard(Account account);

    AccountRsModel getAccount(String username);

    ResponseModel convertAmount(ConversionRqModel request);
}
