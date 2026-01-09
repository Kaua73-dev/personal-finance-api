package com.example.gestaoFinanceiro.Exeptions;

public class CategoryAlreadyExistException extends RuntimeException {
    public CategoryAlreadyExistException() {
        super("Category Already Exist");
    }
}
