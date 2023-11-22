package com.scaler.Splitwise.models;

import com.scaler.Splitwise.constant.UserExpenseType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "SPLITWISE_USER_EXPENSE")
public class UserExpense extends BaseModel{
    private double amount;

    @ManyToOne
    private User user;

    @Enumerated(EnumType.STRING)
    private UserExpenseType userExpenseType;
}
