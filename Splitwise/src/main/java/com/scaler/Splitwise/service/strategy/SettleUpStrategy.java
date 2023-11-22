package com.scaler.Splitwise.service.strategy;

import com.scaler.Splitwise.DTO.TransactionResponseDTO;
import com.scaler.Splitwise.models.Expense;

import java.util.List;

public interface SettleUpStrategy {
    public List<TransactionResponseDTO> settleUp(List<Expense> expenses);
}
