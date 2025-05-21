package com.tiwilli.gerenciamentoestoque.services.cash;

import com.tiwilli.gerenciamentoestoque.dto.cash.CashMovementDTO;
import com.tiwilli.gerenciamentoestoque.entities.cash.CashSession;
import com.tiwilli.gerenciamentoestoque.entities.cash.CashMovement;
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

@Service
public class CashMovementService {

    @Autowired
    private CashMovementRepository repository;

    @Autowired
    private CashSessionRepository cashSessionRepository;

    @Transactional(readOnly = true)
    public CashMovementDTO findById(Long id) {
        CashMovement cashMovement = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Resource not found"));
        return new CashMovementDTO(cashMovement);
    }

    @Transactional(readOnly = true)
    public Page<CashMovementDTO> findAll(Pageable pageable) {
        Page<CashMovement> result = repository.findAll(pageable);
        return result.map(CashMovementDTO::new);
    }

    @Transactional
    public CashMovementDTO insert(CashMovementDTO dto) {
        CashSession openCash = cashSessionRepository.findByClosingTimeIsNull().orElseThrow(
                () -> new BusinessException("There is no open cash"));

        CashMovement entity = new CashMovement();
        copyDtoToEntity(dto, entity, openCash);
        entity = repository.save(entity);
        return new CashMovementDTO(entity);
    }

    @Transactional
    private void copyDtoToEntity(CashMovementDTO dto, CashMovement entity, CashSession cashClosing) {
        entity.setAmount(dto.getAmount());
        entity.setDescription(dto.getDescription());
        entity.setType(dto.getType());
        entity.setMoment(Instant.now());
        entity.setCashClosing(cashClosing);
    }
}
