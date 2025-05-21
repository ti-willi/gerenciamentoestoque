package com.tiwilli.gerenciamentoestoque.repositories.cash;

import com.tiwilli.gerenciamentoestoque.entities.cash.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Long> {
}
