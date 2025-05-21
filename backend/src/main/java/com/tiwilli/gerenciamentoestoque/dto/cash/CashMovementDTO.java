package com.tiwilli.gerenciamentoestoque.dto.cash;

import com.tiwilli.gerenciamentoestoque.entities.cash.CashMovement;
import com.tiwilli.gerenciamentoestoque.entities.enums.MovementType;

import java.time.Instant;

public class CashMovementDTO {

    private Long id;
    private Instant moment;
    private Double amount;
    private String description;
    private MovementType type;
    private Long cashClosingId;

    public CashMovementDTO() {
    }

    public CashMovementDTO(Long id, Instant moment, Double amount, String description, MovementType type, Long cashClosingId) {
        this.id = id;
        this.moment = moment;
        this.amount = amount;
        this.description = description;
        this.type = type;
        this.cashClosingId = cashClosingId;
    }

    public CashMovementDTO(CashMovement entity) {
        id = entity.getId();
        moment = entity.getMoment();
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

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
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
