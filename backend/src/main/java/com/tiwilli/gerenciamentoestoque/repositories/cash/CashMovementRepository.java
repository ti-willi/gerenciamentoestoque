package com.tiwilli.gerenciamentoestoque.repositories.cash;

import com.tiwilli.gerenciamentoestoque.entities.cash.CashMovement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CashMovementRepository extends JpaRepository<CashMovement, Long> {
}
