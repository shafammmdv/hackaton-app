package com.app.model.response;

import com.app.entity.Card;
import java.util.List;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountRsModel {
    String accountId;
    String accountName;
    List<CardRsModel> cards;
}
