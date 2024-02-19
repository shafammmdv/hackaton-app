package com.app.model.response;


import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransactionRsModel {
    String transactionId;

    LocalDateTime dateTime;

    BigDecimal amount;

    String type;

    @JsonInclude(NON_NULL)
    String accountId;

    @JsonInclude(NON_NULL)
    String categoryId;
}
