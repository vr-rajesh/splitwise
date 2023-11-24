package com.scaler.Splitwise.service.InitializeServices;

import com.scaler.Splitwise.DTO.TransactionResponseDTO;
import com.scaler.Splitwise.exception.GroupNotFoundException;

import java.util.List;

public interface GroupService {
    public List<TransactionResponseDTO> settleUpGroupById(int groupId) throws GroupNotFoundException;
}
