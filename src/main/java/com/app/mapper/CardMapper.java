package com.app.mapper;

import com.app.entity.Card;
import com.app.model.response.CardRsModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class CardMapper {

    public static CardMapper CARD_MAPPER_INSTANCE = Mappers.getMapper(CardMapper.class);

    public abstract CardRsModel buildCardRsModel(Card card);
}
