package com.example.gestaoFinanceiro.Exeptions.expenses;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ExpenseAlreadyExistException extends RuntimeException {
    public ExpenseAlreadyExistException() {
        super("Expenses Already Exist");
    }
}
