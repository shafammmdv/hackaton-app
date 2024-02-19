package com.app.mapper;

import com.app.entity.Transaction;
import com.app.model.response.TransactionRsModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class TransactionMapper {

    public static final TransactionMapper TRANSACTION_MAPPER_INSTANCE = Mappers.getMapper(TransactionMapper.class);

    public abstract TransactionRsModel buildGenericResponseModel(Transaction transaction);

}
