package com.example.gestaoFinanceiro.service;


import com.example.gestaoFinanceiro.Exeptions.category.CategoryAlreadyExistException;
import com.example.gestaoFinanceiro.Exeptions.expenses.ExpenseAlreadyExistException;
import com.example.gestaoFinanceiro.Exeptions.expenses.ExpensesNotFoundException;
import com.example.gestaoFinanceiro.auth.AuthVerifyService;
import com.example.gestaoFinanceiro.dto.request.ExpensesRequest;
import com.example.gestaoFinanceiro.dto.response.ExpensesResponse;
import com.example.gestaoFinanceiro.dto.response.RevenuesResponse;
import com.example.gestaoFinanceiro.entity.model.Expenses;
import com.example.gestaoFinanceiro.entity.model.Revenues;
import com.example.gestaoFinanceiro.entity.model.User;
import com.example.gestaoFinanceiro.entity.repository.ExpensesRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ExpensesService extends AuthVerifyService {

    private final ExpensesRepository expensesRepository;

    public ExpensesService(ExpensesRepository expensesRepository) {
        this.expensesRepository = expensesRepository;
    }


    private ExpensesResponse toResponse(Expenses e){
        return new ExpensesResponse(
                e.getNameCategory(),
                e.getDate(),
                e.getValue(),
                e.getDescription()

        );
    }




    public ExpensesResponse createExpenses(ExpensesRequest request){

        User user = getAuthenticatedUser();

        if(expensesRepository.findByDescriptionAndUser(request.description(), user).isPresent()){
            throw new ExpenseAlreadyExistException();
        }

        if(expensesRepository.findByNameCategoryAndUser(request.nameCategory(), user).isPresent()){
            throw new CategoryAlreadyExistException();
        }


        Expenses expenses = new Expenses();
        expenses.setNameCategory(request.nameCategory());
        expenses.setDate(request.date());
        expenses.setValue(request.value());
        expenses.setDescription(request.description());
        expenses.setUser(user);


        Expenses expensesSaved = expensesRepository.save(expenses);


        return new ExpensesResponse(
          expensesSaved.getNameCategory(),
          expensesSaved.getDate(),
          expensesSaved.getValue(),
          expensesSaved.getDescription()

        );

    }


    public ExpensesResponse getExpensesByNameCategory(String nameCategory){
        User user = getAuthenticatedUser();
        


        Expenses expenses = expensesRepository.findByNameCategoryAndUser(nameCategory, user).orElseThrow(() ->
                new ExpensesNotFoundException()
                );

        
        return new ExpensesResponse(
                expenses.getNameCategory(),
                expenses.getDate(),
                expenses.getValue(),
                expenses.getDescription()
                
        );

    }


    public List<ExpensesResponse> getExpensesByDateBetween(int year, int month){

         User user = getAuthenticatedUser();

         LocalDate start = LocalDate.of(year, month, 1);
         LocalDate end = start.plusMonths(1);


         return expensesRepository.findByUserAndDateBetween(user, start, end)
                 .stream()
                 .map(this::toResponse).toList();

    }









}
