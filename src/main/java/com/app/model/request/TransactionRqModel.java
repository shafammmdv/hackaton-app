package com.app.model.request;

import java.math.BigDecimal;
import javax.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransactionRqModel {
    @NotBlank
    String type;

    @NotBlank
    BigDecimal amount;

    @NotBlank
    String mcc;

    @NotBlank
    String phoneNumber;
}
