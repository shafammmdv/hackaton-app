package com.app.controller;

import static com.app.util.Constant.TRANSACTIONS_URL;
import static org.springframework.http.HttpStatus.CREATED;

import com.app.model.ResponseModel;
import com.app.model.request.TransactionRqModel;
import com.app.model.response.TransactionRsModel;
import com.app.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping(TRANSACTIONS_URL)
    public ResponseEntity<ResponseModel<TransactionRsModel>> createTransaction(
            @RequestBody TransactionRqModel requestBody) {

        var response = ResponseModel.of(
                transactionService.createTransaction(requestBody), CREATED);

        return ResponseEntity.ok(response);
    }


}
