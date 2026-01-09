package com.example.gestaoFinanceiro.controller;



import com.example.gestaoFinanceiro.dto.request.ExpensesRequest;
import com.example.gestaoFinanceiro.dto.response.ExpensesResponse;
import com.example.gestaoFinanceiro.entity.model.Expenses;
import com.example.gestaoFinanceiro.service.ExpensesService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class ExpensesController {

    private final ExpensesService expensesService;

    public ExpensesController(ExpensesService expensesService) {
        this.expensesService = expensesService;
    }


    @PostMapping("/expenses")
    public ExpensesResponse createExpenses(@RequestBody ExpensesRequest request){
        return expensesService.createExpenses(request);
    }

    @GetMapping("/expenses/{nameCategory}")
    public ExpensesResponse getExpensesByNameCategory(@PathVariable String nameCategory){
        return expensesService.getExpensesByNameCategory(nameCategory);
    }

}
