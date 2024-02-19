package com.app.controller;

import static com.app.util.Constant.USER_SIGNUP_URL;
import static org.springframework.http.HttpStatus.CREATED;

import com.app.model.ResponseModel;
import com.app.model.request.UserRqModel;
import com.app.model.response.UserRsModel;
import com.app.service.UserService;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping(USER_SIGNUP_URL)
    public ResponseEntity<ResponseModel<UserRsModel>> signup(@RequestBody @Valid UserRqModel requestBody) {

        var response = ResponseModel.of(userService.signup(requestBody), CREATED);
        return ResponseEntity.ok(response);
    }
}
