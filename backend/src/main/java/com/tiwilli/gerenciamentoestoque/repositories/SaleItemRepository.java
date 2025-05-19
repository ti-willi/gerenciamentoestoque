package com.tiwilli.gerenciamentoestoque.repositories;

import com.tiwilli.gerenciamentoestoque.entities.SaleItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleItemRepository extends JpaRepository<SaleItem, Long> {
}
