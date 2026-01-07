package com.example.gestaoFinanceiro.dto.request;

import java.math.BigDecimal;
import java.time.LocalDate;

public record RevenuesRequest(BigDecimal value, LocalDate date, String description) {
}
