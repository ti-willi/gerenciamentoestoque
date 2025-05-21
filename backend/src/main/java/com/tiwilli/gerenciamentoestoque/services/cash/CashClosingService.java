package com.tiwilli.gerenciamentoestoque.services.cash;

import com.tiwilli.gerenciamentoestoque.dto.cash.CashClosingDTO;
import com.tiwilli.gerenciamentoestoque.entities.cash.CashClosing;
import com.tiwilli.gerenciamentoestoque.repositories.cash.CashClosingRepository;
import com.tiwilli.gerenciamentoestoque.repositories.cash.CashMovementRepository;
import com.tiwilli.gerenciamentoestoque.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
public class CashClosingService {

    @Autowired
    private CashClosingRepository repository;

    @Autowired
    private CashMovementRepository cashMovementRepository;

    @Transactional(readOnly = true)
    public CashClosingDTO findById(Long id) {
        CashClosing entity = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Resource not found"));
        return new CashClosingDTO(entity);
    }

    @Transactional(readOnly = true)
    public Page<CashClosingDTO> findAll(Pageable pageable) {
        Page<CashClosing> result = repository.findAll(pageable);
        return result.map(CashClosingDTO::new);
    }

    @Transactional
    public CashClosingDTO openCash(Double initialBalance) {
        CashClosing entity = new CashClosing();
        entity.setOpeningTime(Instant.now());
        entity.setInitialBalance(initialBalance);
        entity = repository.save(entity);
        return new CashClosingDTO(entity);
    }

    @Transactional
    public CashClosingDTO closeCash(Long id, Double finalBalance) {
        CashClosing entity = repository.getReferenceById(id);
        entity.setClosingTime(Instant.now());
        entity.setFinalBalance(finalBalance);
        entity = repository.save(entity);
        return new CashClosingDTO(entity);
    }
}
