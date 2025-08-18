package com.tiwilli.gerenciamentoestoque.projections.cash;

import java.time.Instant;

public interface SalesSummaryProjection {

    Instant getPeriod();
    Double getTotal();
}
