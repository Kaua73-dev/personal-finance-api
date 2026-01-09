package com.example.gestaoFinanceiro.service;


import com.example.gestaoFinanceiro.Exeptions.category.CategoryAlreadyExistException;
import com.example.gestaoFinanceiro.Exeptions.expenses.ExpenseAlreadyExistException;
import com.example.gestaoFinanceiro.auth.AuthVerifyService;
import com.example.gestaoFinanceiro.dto.request.ExpensesRequest;
import com.example.gestaoFinanceiro.dto.response.ExpensesResponse;
import com.example.gestaoFinanceiro.entity.model.Expenses;
import com.example.gestaoFinanceiro.entity.model.User;
import com.example.gestaoFinanceiro.entity.repository.ExpensesRepository;
import org.springframework.stereotype.Service;

@Service
public class ExpensesService extends AuthVerifyService {

    private final ExpensesRepository expensesRepository;

    public ExpensesService(ExpensesRepository expensesRepository) {
        this.expensesRepository = expensesRepository;
    }







    public ExpensesResponse createExpenses(ExpensesRequest request){

        User user = getAuthenticatedUser();

        if(expensesRepository.findByDescriptionAndUser(request.description(), user).isPresent()){
            throw new ExpenseAlreadyExistException();
        }

        if(expensesRepository.findNameCategoryAndUser(request.nameCategory(), user).isPresent()){
            throw new CategoryAlreadyExistException();
        }


        Expenses expenses = new Expenses();
        expenses.get



    }










}
