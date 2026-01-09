package com.example.gestaoFinanceiro.Exeptions.category;

public class CategoryAlreadyExistException extends RuntimeException {
    public CategoryAlreadyExistException() {
        super("Category Already Exist");
    }
}
