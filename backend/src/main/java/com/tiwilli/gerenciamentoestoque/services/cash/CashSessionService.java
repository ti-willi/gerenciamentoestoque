package com.tiwilli.gerenciamentoestoque.services.cash;

import com.tiwilli.gerenciamentoestoque.dto.cash.CashSessionDTO;
import com.tiwilli.gerenciamentoestoque.entities.cash.CashMovement;
import com.tiwilli.gerenciamentoestoque.entities.cash.CashSession;
import com.tiwilli.gerenciamentoestoque.entities.enums.MovementType;
import com.tiwilli.gerenciamentoestoque.repositories.cash.CashSessionRepository;
import com.tiwilli.gerenciamentoestoque.repositories.cash.CashMovementRepository;
import com.tiwilli.gerenciamentoestoque.services.exceptions.BusinessException;
import com.tiwilli.gerenciamentoestoque.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class CashSessionService {

    @Autowired
    private CashSessionRepository repository;

    @Autowired
    private CashMovementRepository cashMovementRepository;

    @Transactional(readOnly = true)
    public CashSessionDTO findById(Long id) {
        CashSession entity = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Resource not found"));
        return new CashSessionDTO(entity);
    }

    @Transactional(readOnly = true)
    public Page<CashSessionDTO> findAll(Pageable pageable) {
        Page<CashSession> result = repository.findAll(pageable);
        return result.map(CashSessionDTO::new);
    }

    @Transactional
    public CashSessionDTO openCash(Double initialBalance) {
        Optional<CashSession> openCash = repository.findByClosingTimeIsNull();
        if (openCash.isPresent()) {
            throw new BusinessException("There is already a cash open. Close it before opening a new one.");
        }

        CashSession entity = new CashSession();
        entity.setOpeningTime(Instant.now());
        entity.setInitialBalance(initialBalance);
        entity = repository.save(entity);
        return new CashSessionDTO(entity);
    }

    @Transactional
    public CashSessionDTO closeCash() {
        CashSession entity = repository.findByClosingTimeIsNull().orElseThrow(
                () -> new BusinessException("There is no open cash to close."));

        List<CashMovement> movements = entity.getMovements();
        double finalBalance = calculateFinalBalance(movements, entity.getInitialBalance());

        entity.setClosingTime(Instant.now());
        entity.setFinalBalance(finalBalance);
        entity = repository.save(entity);
        return new CashSessionDTO(entity);
    }

    private double calculateFinalBalance(List<CashMovement> movements, double initialBalance) {
        double totalIncome = movements.stream()
                .filter(m -> m.getType() == MovementType.INCOME)
                .mapToDouble(CashMovement::getAmount)
                .sum();

        double totalExpense = movements.stream()
                .filter(m -> m.getType() == MovementType.EXPENSE)
                .mapToDouble(CashMovement::getAmount)
                .sum();

        return initialBalance + totalIncome - totalExpense;
    }
}
