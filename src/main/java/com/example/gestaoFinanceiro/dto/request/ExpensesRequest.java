package com.example.gestaoFinanceiro.dto.request;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ExpensesRequest(String nameCategory, LocalDate date, BigDecimal value, String description) {
}
