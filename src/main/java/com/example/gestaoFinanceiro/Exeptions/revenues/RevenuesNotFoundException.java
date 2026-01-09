package com.example.gestaoFinanceiro.Exeptions.revenues;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RevenuesNotFoundException extends RuntimeException {
    public RevenuesNotFoundException() {
        super("Revenues not found");
    }
}
