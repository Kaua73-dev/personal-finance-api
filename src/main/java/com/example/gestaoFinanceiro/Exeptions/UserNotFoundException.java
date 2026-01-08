package com.example.gestaoFinanceiro.Exeptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("user not found");
    }
}
