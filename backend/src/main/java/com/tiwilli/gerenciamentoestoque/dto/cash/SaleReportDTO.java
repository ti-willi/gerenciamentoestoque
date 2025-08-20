package com.tiwilli.gerenciamentoestoque.dto.cash;

import java.time.Instant;
import java.util.List;

public record SaleReportDTO(
        Long saleId,
        Instant saleDate,
        Double totalAmount,
        List<SaleItemDTO> items
) {}
