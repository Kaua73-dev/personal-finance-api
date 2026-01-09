package com.example.gestaoFinanceiro.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ExpensesResponse(String nameCategory, LocalDate date, BigDecimal value, String description) {
}
