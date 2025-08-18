package com.tiwilli.gerenciamentoestoque.dto.cash;

public record SalesSummaryDTO (
        String period,
        Long totalQuantity,
        Double totalAmount
) {}
