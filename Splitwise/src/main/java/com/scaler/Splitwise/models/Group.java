package com.scaler.Splitwise.models;

import com.scaler.Splitwise.constant.Currency;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity(name = "SPLITWISE_GROUP")
public class Group extends BaseModel{

    private String groupName;
    private String groupDescription;
    private double totalAmountSpent;

    @Enumerated(EnumType.STRING)
    private Currency defaultCurrency;
    @ManyToMany
    private List<User> users;
    @OneToMany
    @JoinColumn(name = "splitwise_group_id")
    private List<Expense> expenses;

}
