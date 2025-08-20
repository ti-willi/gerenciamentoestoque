package com.tiwilli.gerenciamentoestoque.dto.cash;

import org.springframework.data.domain.Page;

public record SalesReportDTO(
        Integer totalQuantity,
        Double totalAmount,
        Page<SaleReportDTO> sales
) {}
