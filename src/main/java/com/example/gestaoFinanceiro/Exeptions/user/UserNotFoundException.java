package com.example.gestaoFinanceiro.Exeptions.user;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("user not found");
    }
}
