package com.scaler.Splitwise.controller;

import com.scaler.Splitwise.DTO.TransactionResponseDTO;
import com.scaler.Splitwise.exception.GroupNotFoundException;
import com.scaler.Splitwise.service.InitializeServices.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GroupController {

    @Autowired
    GroupService groupService;

    @GetMapping("/settleUp/{groupId}")
    public ResponseEntity calculateGroupExpense(int groupId) throws GroupNotFoundException {
        List<TransactionResponseDTO> transactionResponseDTO = groupService.settleUpGroupById(groupId);
        return ResponseEntity.ok(transactionResponseDTO);
    }

}
