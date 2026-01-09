package com.example.gestaoFinanceiro.Exeptions.expenses;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ExpensesNotFoundException extends RuntimeException {
    public ExpensesNotFoundException() {
        super("Exception Not Found");
    }
}
