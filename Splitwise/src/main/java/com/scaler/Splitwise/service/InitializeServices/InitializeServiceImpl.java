package com.scaler.Splitwise.service.InitializeServices;

import com.scaler.Splitwise.constant.Currency;
import com.scaler.Splitwise.constant.UserExpenseType;
import com.scaler.Splitwise.models.Expense;
import com.scaler.Splitwise.models.Group;
import com.scaler.Splitwise.models.User;
import com.scaler.Splitwise.models.UserExpense;
import com.scaler.Splitwise.repository.ExpenseRepository;
import com.scaler.Splitwise.repository.GroupRepository;
import com.scaler.Splitwise.repository.UserExpenseRepository;
import com.scaler.Splitwise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InitializeServiceImpl implements InitializeService{

    @Autowired
    private UserRepository userRepository;

    private GroupRepository groupRepository;

    private ExpenseRepository expenseRepository;

    private UserExpenseRepository userExpenseRepository;

    @Override
    public void init() {

        Group goaTrip = new Group();
        goaTrip.setGroupName("Goa Trip");
        goaTrip.setGroupDescription("Go Goa Gone");
        goaTrip.setDefaultCurrency(Currency.INR);
        Group savedGoaGroup = groupRepository.save(goaTrip);


        User rajesh =  new User();
        rajesh.setName("Rajesh Kumar");
        rajesh.setEmail("rajeshkumarraju16@gmail.com");
        rajesh.setPhoneNumber("6309429908");
        rajesh.setGroups((List<Group>) savedGoaGroup);

        User ziya =  new User();
        ziya.setName("Ziya Mohd");
        ziya.setEmail("ziyamohd@gmail.com");
        ziya.setPhoneNumber("8809429908");
        ziya.setGroups((List<Group>) savedGoaGroup);

        User rahul =  new User();
        rahul.setName("Rahul Akavarpu");
        rahul.setEmail("rahulakavarapu@gmail.com");
        rahul.setPhoneNumber("9309429908");
        rahul.setGroups((List<Group>) savedGoaGroup);

        User pooja  =  new User();
        pooja.setName("Pooja");
        pooja.setEmail("pooja@gmail.com");
        pooja.setPhoneNumber("9836753456");
        pooja.setGroups((List<Group>) savedGoaGroup);


        /*The reason why we are storing the saved DB entries in the var because once we save the id and automatic generated
        * value will come in DB , this will allow PK and FK mapping */
        User rajeshUser = userRepository.save(rajesh);
        User rahulUser = userRepository.save(rahul);
        User ziyaUser = userRepository.save(ziya);
        User poojaUser = userRepository.save(pooja);

        goaTrip.setUsers(List.of(rajeshUser, rahulUser, ziyaUser, poojaUser));
        groupRepository.save(savedGoaGroup);

        UserExpense userRajeshExpense = new UserExpense();
        userRajeshExpense.setAmount(500);
        userRajeshExpense.setUserExpenseType(UserExpenseType.DEBIT);
        userRajeshExpense.setUser(rajeshUser);
        UserExpense savedUserRajeshExpense = userExpenseRepository.save(userRajeshExpense);

        UserExpense userRahulExpense = new UserExpense();
        userRahulExpense.setAmount(1500);
        userRahulExpense.setUserExpenseType(UserExpenseType.DEBIT);
        userRahulExpense.setUser(rahulUser);
        UserExpense savedUserRahulExpense = userExpenseRepository.save(userRahulExpense);

        UserExpense userZiyaExpense = new UserExpense();
        userZiyaExpense.setAmount(1000);
        userZiyaExpense.setUserExpenseType(UserExpenseType.CREDIT);
        userZiyaExpense.setUser(ziyaUser);
        UserExpense savedUserZiyaExpense = userExpenseRepository.save(userZiyaExpense);

        UserExpense userPoojaExpense = new UserExpense();
        userPoojaExpense.setAmount(1000);
        userPoojaExpense.setUserExpenseType(UserExpenseType.CREDIT);
        userPoojaExpense.setUser(poojaUser);
        UserExpense savedUserPoojaExpense = userExpenseRepository.save(userPoojaExpense);

        Expense goaExpense = new Expense();
        goaExpense.setAmount(2000);
        goaExpense.setExpenseDescription("Total Trip Amount");
        goaExpense.setUserExpenses(List.of(userRajeshExpense, userRahulExpense, userZiyaExpense, userPoojaExpense));
        goaExpense.setCurrency(Currency.INR);
        Expense savedGoaExpense = expenseRepository.save(goaExpense);

        goaTrip.setExpenses(List.of(savedGoaExpense));
        groupRepository.save(savedGoaGroup);

    }
}