package com.example.gestaoFinanceiro.service;


import com.example.gestaoFinanceiro.Exeptions.category.CategoryAlreadyExistException;
import com.example.gestaoFinanceiro.Exeptions.expenses.ExpenseAlreadyExistException;
import com.example.gestaoFinanceiro.Exeptions.expenses.ExpensesNotFoundException;
import com.example.gestaoFinanceiro.auth.AuthVerifyService;
import com.example.gestaoFinanceiro.dto.request.ExpensesRequest;
import com.example.gestaoFinanceiro.dto.response.CategoryTotalResponse;
import com.example.gestaoFinanceiro.dto.response.ExpensesResponse;
import com.example.gestaoFinanceiro.entity.model.Expenses;
import com.example.gestaoFinanceiro.entity.model.User;
import com.example.gestaoFinanceiro.entity.repository.ExpensesRepository;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

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

    public List<ExpensesResponse> getExpenses(){
        User user = getAuthenticatedUser();
        return expensesRepository.findByUser(user).stream().map(this::toResponse).toList();
    }

    public List<ExpensesResponse> getExpensesByDateBetween(int year, int month){

         User user = getAuthenticatedUser();

         LocalDate start = LocalDate.of(year, month, 1);
         LocalDate end = start.plusMonths(1);


         return expensesRepository.findByUserAndDateBetween(user, start, end)
                 .stream()
                 .map(this::toResponse).toList();

    }

    public void deleteExpensesByDescription(String description){
        User user = getAuthenticatedUser();

        Expenses expenses = expensesRepository.findByDescriptionAndUser(description, user).orElseThrow(() ->
                new ExpensesNotFoundException()

        );

         expensesRepository.delete(expenses);

    }

    public ExpensesResponse updateExpenseByDescription(ExpensesRequest request, String description){

        User user = getAuthenticatedUser();


        Expenses expenses = expensesRepository.findByDescriptionAndUser(description, user).orElseThrow(() ->
                new ExpensesNotFoundException()
                );

        if(request.nameCategory() != null){
            expenses.setNameCategory(request.nameCategory());
        }

        if(request.date() != null){
            expenses.setDate(request.date());
        }

        if(request.value() != null){
            expenses.setValue(request.value());
        }

        if(request.description() != null){
            expenses.setDescription(request.description());
        }


        expensesRepository.save(expenses);


        return new ExpensesResponse(
           expenses.getNameCategory(),
           expenses.getDate(),
           expenses.getValue(),
           expenses.getDescription()
        );


    }

    public BigDecimal getTotalExpensesUserByDate(int year, int month){

    User user = getAuthenticatedUser();

    LocalDate start = LocalDate.of(year, month, 1);
    LocalDate end = start.plusMonths(1);

    return expensesRepository.totalExpensesByUser(user, start, end);

    }

    public List<CategoryTotalResponse> getTotalByCategory(int year, int month){

        User user = getAuthenticatedUser();

        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.plusMonths(1);

        return expensesRepository.totalExpensesByCategory(user, start, end);

    }




}
