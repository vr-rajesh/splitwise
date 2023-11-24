package com.scaler.Splitwise.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponseDTO {
    private String fromUserName;
    private String toUserName;
    private double amount;
}
