package com.example.gestaoFinanceiro.Exeptions.revenues;

public class RevenuesAlreadyExistException extends RuntimeException {
    public RevenuesAlreadyExistException() {
        super("Revenues already Exist");
    }
}
