package com.example.gestaoFinanceiro.Exeptions;

public class RevenuesNotFoundException extends RuntimeException {
    public RevenuesNotFoundException() {
        super("Revenues not found");
    }
}
