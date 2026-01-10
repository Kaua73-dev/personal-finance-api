package com.example.gestaoFinanceiro.dto.response;

import java.math.BigDecimal;

public record CategoryTotalResponse(String category, BigDecimal total) {
}
