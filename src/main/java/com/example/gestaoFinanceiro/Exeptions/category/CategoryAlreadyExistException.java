package com.example.gestaoFinanceiro.Exeptions.category;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CategoryAlreadyExistException extends RuntimeException {
    public CategoryAlreadyExistException() {
        super("Category Already Exist");
    }
}
