package com.scaler.Splitwise.service.InitializeServices;

import com.scaler.Splitwise.DTO.TransactionResponseDTO;
import com.scaler.Splitwise.exception.GroupNotFoundException;
import com.scaler.Splitwise.models.Group;
import com.scaler.Splitwise.repository.GroupRepository;
import com.scaler.Splitwise.service.strategy.SettleUpStrategy;
import com.scaler.Splitwise.service.strategy.SettleUpStrategyFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupServiceImpl implements GroupService{
    private GroupRepository groupRepository;

    @Override
    public List<TransactionResponseDTO> settleUpGroupById(int groupId) throws GroupNotFoundException {
        SettleUpStrategy strategy = SettleUpStrategyFactory.getSettleUpStrategyFactory();
        Optional<Group> savedGroup = groupRepository.findById(groupId);
        if(savedGroup.isEmpty()){
            throw new GroupNotFoundException("Group with ID : " + groupId + " is not available");
        }
        return  strategy.settleUp(savedGroup.get().getExpenses());
    }
}
