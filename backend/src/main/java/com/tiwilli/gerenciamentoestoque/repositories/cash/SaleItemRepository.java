package com.tiwilli.gerenciamentoestoque.repositories.cash;

import com.tiwilli.gerenciamentoestoque.entities.cash.SaleItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleItemRepository extends JpaRepository<SaleItem, Long> {
}
