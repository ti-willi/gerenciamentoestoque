package com.tiwilli.gerenciamentoestoque.dtos;

import com.tiwilli.gerenciamentoestoque.entities.CashMovement;
import com.tiwilli.gerenciamentoestoque.entities.enums.MovementType;

import java.time.LocalDateTime;

public class CashMovementDTO {

    private Long id;
    private LocalDateTime localDateTime;
    private Double amount;
    private String description;
    private MovementType type;
    private Long cashClosingId;

    public CashMovementDTO() {
    }

    public CashMovementDTO(Long id, LocalDateTime localDateTime, Double amount, String description, MovementType type, Long cashClosingId) {
        this.id = id;
        this.localDateTime = localDateTime;
        this.amount = amount;
        this.description = description;
        this.type = type;
        this.cashClosingId = cashClosingId;
    }

    public CashMovementDTO(CashMovement entity) {
        id = entity.getId();
        localDateTime = entity.getLocalDateTime();
        amount = entity.getAmount();
        description = entity.getDescription();
        type = entity.getType();
        cashClosingId = entity.getCashClosing().getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MovementType getType() {
        return type;
    }

    public void setType(MovementType type) {
        this.type = type;
    }

    public Long getCashClosingId() {
        return cashClosingId;
    }

    public void setCashClosingId(Long cashClosingId) {
        this.cashClosingId = cashClosingId;
    }
}
