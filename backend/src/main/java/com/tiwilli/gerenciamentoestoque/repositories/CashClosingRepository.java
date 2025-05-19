package com.tiwilli.gerenciamentoestoque.repositories;

import com.tiwilli.gerenciamentoestoque.entities.CashClosing;
import com.tiwilli.gerenciamentoestoque.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CashClosingRepository extends JpaRepository<CashClosing, Long> {
}
