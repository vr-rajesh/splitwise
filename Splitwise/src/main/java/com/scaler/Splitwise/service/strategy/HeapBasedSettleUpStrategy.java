package com.scaler.Splitwise.service.strategy;

import com.scaler.Splitwise.DTO.TransactionResponseDTO;
import com.scaler.Splitwise.constant.UserExpenseType;
import com.scaler.Splitwise.models.Expense;
import com.scaler.Splitwise.models.User;
import com.scaler.Splitwise.models.UserExpense;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.text.StyleContext;
import java.util.*;

public class HeapBasedSettleUpStrategy implements SettleUpStrategy{

    private static final Logger logger = LoggerFactory.getLogger(HeapBasedSettleUpStrategy.class);

    @Override
    public List<TransactionResponseDTO> settleUp(List<Expense> expenses) {
        List<TransactionResponseDTO> transactionResponseDTOS = new ArrayList<>();
        Map<User, Double> outStandingAmountMap = new HashMap<User, Double>();
        for(Expense expense : expenses){
            for(UserExpense userExpense : expense.getUserExpenses()){
                User user = userExpense.getUser();
                Double currentOutStandingAmount = outStandingAmountMap.getOrDefault(user, 0.0);
                outStandingAmountMap.put(user, calculateCurrentOutstandingAmount(currentOutStandingAmount, userExpense));
            }
        }
        // minHeap to Store the DEBIT
        // maxHeap to Store the CREDIT
        PriorityQueue<Map.Entry<User, Double>> maxHeap = new PriorityQueue<>(
                Map.Entry.<User, Double>comparingByValue(Comparator.reverseOrder()));
        PriorityQueue<Map.Entry<User, Double>> minHeap = new PriorityQueue<>(
                Map.Entry.<User, Double>comparingByValue(Comparator.naturalOrder()));

        // populate the minHeap and maxHeap
        for(Map.Entry<User, Double> entry : outStandingAmountMap.entrySet()){
            if(entry.getValue() < 0)
                minHeap.add(entry);
            else if(entry.getValue() > 0)
                maxHeap.add(entry);
            else
                logger.info("Already Settled Up for User Name : " +  entry.getKey().getName());
        }

        // calculate the transaction until the heap becomes Empty
        while(!minHeap.isEmpty())
        {
            minHeap.poll();
            maxHeap.poll();
        }


        return null;
    }

    private Double calculateCurrentOutstandingAmount(Double currentOutStandingAmount, UserExpense userExpense) {
        double currentAmount = currentOutStandingAmount;
        if(userExpense.getUserExpenseType().equals(UserExpenseType.DEBIT)){
            currentAmount += userExpense.getAmount();
        }
        else{
            currentAmount -= userExpense.getAmount();

        }
        return currentAmount;
    }
}
