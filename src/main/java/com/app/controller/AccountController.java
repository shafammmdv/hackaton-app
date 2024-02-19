package com.app.controller;

import static com.app.util.Constant.ACCOUNT_URL;
import static com.app.util.Constant.CONVERSION_URL;
import static org.springframework.http.HttpStatus.OK;

import com.app.model.ResponseModel;
import com.app.model.request.ConversionRqModel;
import com.app.model.response.AccountRsModel;
import com.app.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class AccountController {

    private final AccountService accountService;

    @GetMapping(ACCOUNT_URL)
    public ResponseEntity<ResponseModel<AccountRsModel>> getAccount(@RequestParam("phone_number") String phoneNumber) {
        var response = ResponseModel.of(
                accountService.getAccount(phoneNumber), OK);

        return ResponseEntity.ok(response);
    }

    @PostMapping(CONVERSION_URL)
    public ResponseEntity<ResponseModel<com.app.model.response.ResponseModel>> convertAmount(
            @RequestBody ConversionRqModel requestBody) {

        var response = ResponseModel.of(
                accountService.convertAmount(requestBody), OK);

        return ResponseEntity.ok(response);
    }




}
