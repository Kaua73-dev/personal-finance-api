package com.example.gestaoFinanceiro.Exeptions.revenues;

public class RevenuesNotFoundException extends RuntimeException {
    public RevenuesNotFoundException() {
        super("Revenues not found");
    }
}
