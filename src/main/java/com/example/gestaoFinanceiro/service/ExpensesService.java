package com.example.gestaoFinanceiro.service;


import com.example.gestaoFinanceiro.dto.request.ExpensesRequest;
import com.example.gestaoFinanceiro.dto.response.ExpensesResponse;
import com.example.gestaoFinanceiro.entity.repository.ExpensesRepository;
import org.springframework.stereotype.Service;

@Service
public class ExpensesService {

    private final ExpensesRepository expensesRepository;

    public ExpensesService(ExpensesRepository expensesRepository) {
        this.expensesRepository = expensesRepository;
    }












}
