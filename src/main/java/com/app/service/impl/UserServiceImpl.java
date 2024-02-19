package com.app.service.impl;

import static com.app.mapper.UserMapper.USER_MAPPER_INSTANCE;
import static com.app.util.Constant.DUPLICATE_FIN_CODE_MSG;
import static com.app.util.Constant.DUPLICATE_PHONE_NUMBER_MSG;
import static com.app.util.Constant.OTP;
import static com.app.util.Constant.ROLE_NOT_FOUND_MSG;

import com.app.entity.Otp;
import com.app.entity.User;
import com.app.exception.DataNotFoundException;
import com.app.exception.DuplicateException;
import com.app.model.request.UserRqModel;
import com.app.model.response.UserRsModel;
import com.app.repository.OtpRepository;
import com.app.repository.RoleRepository;
import com.app.repository.UserRepository;
import com.app.service.AccountService;
import com.app.service.UserService;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Log4j2
public class UserServiceImpl implements UserService {
    private final UserRepository userRepo;
    private final RoleRepository roleRepo;
    private final OtpRepository otpRepo;
    private final AccountService accountService;

    @Override
    public UserRsModel signup(UserRqModel signupRqModel) {
        checkPhoneNumberUniqueness(signupRqModel.getPhoneNumber());
        checkFinCodeUniqueness(signupRqModel.getFinCode());
        User user = userRepo.save(buildUser(signupRqModel));

        otpRepo.save(buildOtp(user));
        accountService.saveAccount(user);
        return USER_MAPPER_INSTANCE.buildUserResponseModel(user);
    }

    private User buildUser(UserRqModel userRqModel) {
        return User.builder()
                .userId(UUID.randomUUID().toString())
                .phoneNumber(userRqModel.getPhoneNumber())
                .finCode(userRqModel.getFinCode())
                .roles(Collections.singletonList(
                        roleRepo.byName("ROLE_USER")
                                .orElseThrow(() -> new DataNotFoundException(ROLE_NOT_FOUND_MSG, 1009))))
                .build();
    }

    private Otp buildOtp(User user) {
        return Otp.builder()
                .otpId(UUID.randomUUID().toString())
                .otp(OTP)
                .creationDate(String.valueOf(LocalDateTime.now()))
                .user(user)
                .build();
    }

    private void checkPhoneNumberUniqueness(String phoneNumber) {
        if (userRepo.findByPhoneNumber(phoneNumber).isPresent()) {
            throw new DuplicateException(DUPLICATE_PHONE_NUMBER_MSG, 1007);
        }
    }

    private void checkFinCodeUniqueness(String finCode) {
        if (userRepo.findByFinCode(finCode).isPresent()) {
            throw new DuplicateException(DUPLICATE_FIN_CODE_MSG, 1001);
        }
    }
}
