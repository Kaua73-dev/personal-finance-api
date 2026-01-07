package com.example.gestaoFinanceiro.service;


import com.example.gestaoFinanceiro.entity.repository.RevenuesRepository;
import org.springframework.stereotype.Service;

@Service
public class RevenuesService {


    private final RevenuesRepository revenuesRepository;


    public RevenuesService(RevenuesRepository revenuesRepository) {
        this.revenuesRepository = revenuesRepository;
    }
}
