package com.app.util;

public class Constant {

    public static final String JWT_PREFIX = "Bearer ";
    public static final String JWT_HEADER = "token";
    public static final String JWT_TOKEN_FORMAT = "%s%s";
    public static final String USER_LOGIN_URL = "/v1/login";
    public static final String USER_SIGNUP_URL = "/v1/users/signup";
    public static final String ACCOUNT_URL = "/v1/accounts";
    public static final String TRANSACTIONS_URL = "/v1/transactions";
    public static final String CONVERSION_URL = "/v1/convert";
    public static final String HEADER_REMEMBER_ME = "rememberMe";
    public static final String INVALID_REQUEST_MODEL_MSG = "Request body is invalid or missed";
    public static final String USER_NOT_FOUND_MSG = "User %s is not found in the database";
    public static final String USER_BY_USERNAME = "User by username -> {}: {}";
    public static final String ALL_ACCOUNTS_MSG = "All accounts of {}: {}";
    public static final String JWT_TOKEN_GENERATED_MSG = "JWT token generated for %s";
    public static final String INVALID_CREDENTIALS_MSG = "Invalid credentials";
    public static final String CONTENT_TYPE_JSON = "application/json";
    public static final String INVALID_PHONE_NUMBER_MSG =
            "Phone number '%s' does not follow valid phone number format rules";
    public static final String DUPLICATE_PHONE_NUMBER_MSG = "Phone number '%s' is duplicate";
    public static final String DUPLICATE_FIN_CODE_MSG = "Fin code '%s' is duplicate";
    public static final String ROLE_NOT_FOUND_MSG = "Role is not found in database";
    public static final String OTP = "11111";
    public static final String ACCOUNT_NOT_FOUND_MSG = "Account  %s is not found";
    public static final String CATEGORY_NOT_FOUND_MSG = "Category  %s is not found";
    public static final String ACCOUNT_TYPE_NOT_FOUND_MSG = "Account type  %s is not found";
    public static final String ACCOUNT_TYPE_BY_NAME_MSG = "Account type by name -> {}: {}";
    public static final String ACCOUNT_NAME = "Kart hesabı";
    public static final String DIGITAL_CARD_NAME = "cardAccount";
    public static final String COMMISSION_CARD_NAME = "commissionAccount";
    public static final String BONUS_CARD_NAME = "bonusAccount";
    public static final String CONVERSION_COMMISSION_TYPE = "commission";
    public static final String CONVERSION_CASHBACK_TYPE = "cashback";
    public static final String CONVERSION_RESPONSE_MESSAGE = "Amount successfully converted";




}
