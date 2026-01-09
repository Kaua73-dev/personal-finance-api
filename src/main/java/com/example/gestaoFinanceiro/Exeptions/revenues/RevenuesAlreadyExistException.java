package com.example.gestaoFinanceiro.Exeptions.revenues;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class RevenuesAlreadyExistException extends RuntimeException {
    public RevenuesAlreadyExistException() {
        super("Revenues already Exist");
    }
}
