package com.example.gestaoFinanceiro.Exeptions;

public class RevenuesAlreadyExistException extends RuntimeException {
    public RevenuesAlreadyExistException() {
        super("Revenues already Exist");
    }
}
