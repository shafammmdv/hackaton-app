package com.app.service;

import com.app.model.request.UserRqModel;
import com.app.model.response.UserRsModel;

public interface UserService {

    UserRsModel signup(UserRqModel signupRqModel);
}
