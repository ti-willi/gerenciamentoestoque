package com.tiwilli.gerenciamentoestoque.dto.cash;

import com.tiwilli.gerenciamentoestoque.entities.cash.CashSession;
import com.tiwilli.gerenciamentoestoque.entities.cash.CashMovement;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class CashSessionDTO {

    private Long id;
    private Instant openingTime;
    private Instant closingTime;
    private Double initialBalance;
    private Double finalBalance;
    private List<CashMovementDTO> movements = new ArrayList<>();

    public CashSessionDTO() {
    }

    public CashSessionDTO(Long id, Instant openingTime, Instant closingTime, Double initialBalance, Double finalBalance) {
        this.id = id;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.initialBalance = initialBalance;
        this.finalBalance = finalBalance;
    }

    public CashSessionDTO(CashSession entity) {
        id = entity.getId();
        openingTime = entity.getOpeningTime();
        closingTime = entity.getClosingTime();
        initialBalance = entity.getInitialBalance();
        finalBalance = entity.getFinalBalance();
        for (CashMovement movement : entity.getMovements()) {
            CashMovementDTO movementDTO = new CashMovementDTO(movement);
            movements.add(movementDTO);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(Instant openingTime) {
        this.openingTime = openingTime;
    }

    public Instant getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(Instant closingTime) {
        this.closingTime = closingTime;
    }

    public Double getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(Double initialBalance) {
        this.initialBalance = initialBalance;
    }

    public Double getFinalBalance() {
        return finalBalance;
    }

    public void setFinalBalance(Double finalBalance) {
        this.finalBalance = finalBalance;
    }

    public List<CashMovementDTO> getMovements() {
        return movements;
    }
}
