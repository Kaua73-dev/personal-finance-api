package com.example.gestaoFinanceiro.Exeptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CategoryAlreadyExistExeption extends RuntimeException {
    public CategoryAlreadyExistExeption() {
        super("Category already exist");
    }
}
