package com.tiwilli.gerenciamentoestoque.repositories.cash;

import com.tiwilli.gerenciamentoestoque.entities.cash.Sale;
import com.tiwilli.gerenciamentoestoque.projections.cash.SalesSummaryProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("""
            SELECT obj FROM Sale obj
            WHERE (:startMoment IS NULL OR obj.moment >= :startMoment)
            AND (:endMoment IS NULL OR obj.moment <= :endMoment)
            """)
    Page<Sale> searchByDateBetween(Instant startMoment, Instant endMoment, Pageable pageable);

}
