package com.app.mapper;

import com.app.entity.User;
import com.app.model.response.UserRsModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class UserMapper {

    public static UserMapper USER_MAPPER_INSTANCE = Mappers.getMapper(UserMapper.class);

    public abstract UserRsModel buildUserResponseModel(User user);
}
