package com.app.service;

import com.app.model.request.TransactionRqModel;
import com.app.model.response.TransactionRsModel;

public interface TransactionService {
    TransactionRsModel createTransaction(TransactionRqModel transactionRqModel);
}
