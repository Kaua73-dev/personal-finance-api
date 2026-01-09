package com.example.gestaoFinanceiro.controller;



import com.example.gestaoFinanceiro.service.ExpensesService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class ExpensesController {



    private final ExpensesService expensesService;

    public ExpensesController(ExpensesService expensesService) {
        this.expensesService = expensesService;
    }






}
