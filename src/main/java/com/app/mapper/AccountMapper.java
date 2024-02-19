package com.app.mapper;

import static com.app.mapper.CardMapper.CARD_MAPPER_INSTANCE;

import com.app.entity.Account;
import com.app.model.response.AccountRsModel;
import com.app.model.response.CardRsModel;
import java.util.stream.Collectors;
import org.aspectj.lang.annotation.After;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class AccountMapper {

    public static AccountMapper ACCOUNT_MAPPER_INSTANCE = Mappers.getMapper(AccountMapper.class);

    @Mapping(target = "accountName", source = "name")
    @Mapping(target = "cards", ignore = true)
    public abstract AccountRsModel buildAccountResponseModel(Account account);

    @AfterMapping
    void mapCard(@MappingTarget AccountRsModel.AccountRsModelBuilder response, Account account) {
        response.cards(account.getCards().stream().map(CARD_MAPPER_INSTANCE::buildCardRsModel).collect(Collectors.toList()));
    }
}
