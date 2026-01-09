package com.example.gestaoFinanceiro.controller;



import com.example.gestaoFinanceiro.dto.request.ExpensesRequest;
import com.example.gestaoFinanceiro.dto.response.ExpensesResponse;
import com.example.gestaoFinanceiro.service.ExpensesService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
