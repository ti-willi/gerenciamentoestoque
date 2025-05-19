package com.tiwilli.gerenciamentoestoque.repositories;

import com.tiwilli.gerenciamentoestoque.entities.CashClosing;
import com.tiwilli.gerenciamentoestoque.entities.CashMovement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CashMovementRepository extends JpaRepository<CashMovement, Long> {
}
