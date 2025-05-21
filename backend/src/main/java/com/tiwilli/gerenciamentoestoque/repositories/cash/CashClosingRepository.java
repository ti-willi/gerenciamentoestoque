package com.tiwilli.gerenciamentoestoque.repositories.cash;

import com.tiwilli.gerenciamentoestoque.entities.cash.CashClosing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CashClosingRepository extends JpaRepository<CashClosing, Long> {
}
