package com.example.gestaoFinanceiro.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;

public record RevenuesResponse(BigDecimal value, LocalDate date, String description) {
}
