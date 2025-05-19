package com.tiwilli.gerenciamentoestoque.repositories;

import com.tiwilli.gerenciamentoestoque.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Long> {
}
