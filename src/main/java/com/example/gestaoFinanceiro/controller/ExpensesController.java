package com.example.gestaoFinanceiro.controller;



import com.example.gestaoFinanceiro.dto.request.ExpensesRequest;
import com.example.gestaoFinanceiro.dto.response.CategoryTotalResponse;
import com.example.gestaoFinanceiro.dto.response.ExpensesResponse;
import com.example.gestaoFinanceiro.service.ExpensesService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;


@RestController
@RequestMapping("/auth")
public class ExpensesController {

    private final ExpensesService expensesService;

    public ExpensesController(ExpensesService expensesService) {
        this.expensesService = expensesService;
    }


    @PostMapping("/expenses")
    public ExpensesResponse createExpenses(@RequestBody ExpensesRequest request) {
        return expensesService.createExpenses(request);
    }

    @GetMapping("/expenses")
    public List<ExpensesResponse> getExpenses(){
        return expensesService.getExpenses();
    }

    @GetMapping("/expenses/{year}/{month}")
    public List<ExpensesResponse> getExpensesByDate(@PathVariable int year, int month) {
        return expensesService.getExpensesByDateBetween(year, month);
    }

    @DeleteMapping("/expenses/{description}")
    public void deleteExpenseByDescription(String description) {
        expensesService.deleteExpensesByDescription(description);
    }

    @PutMapping("/expenses/{description}")
    public ExpensesResponse updateExpensesByDescription(@PathVariable String description, @RequestBody ExpensesRequest request) {
        return expensesService.updateExpenseByDescription(request, description);
    }


    @GetMapping("/expenses/totalUser/{year}/{month}")
    public BigDecimal getTotalByUser(@PathVariable int year, @PathVariable int month) {
        return expensesService.getTotalExpensesUserByDate(year, month);
    }

    @GetMapping("/expenses/totalCategory/{year}/{month}")
    public List<CategoryTotalResponse> getTotalByCategory(@PathVariable int year, @PathVariable int month) {
        return expensesService.getTotalByCategory(year, month);
    }

}
