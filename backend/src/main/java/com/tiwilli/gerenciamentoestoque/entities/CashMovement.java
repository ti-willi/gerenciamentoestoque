package com.tiwilli.gerenciamentoestoque.entities;

import com.tiwilli.gerenciamentoestoque.entities.enums.MovementType;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "tb_cash_movement")
public class CashMovement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime localDateTime;
    private Double amount;
    private String description;
    private MovementType type;

    @ManyToOne
    @JoinColumn(name = "cash_closing_id")
    private CashClosing cashClosing;

    public CashMovement() {
    }

    public CashMovement(Long id, LocalDateTime localDateTime, Double amount, String description, MovementType type, CashClosing cashClosing) {
        this.id = id;
        this.localDateTime = localDateTime;
        this.amount = amount;
        this.description = description;
        this.type = type;
        this.cashClosing = cashClosing;
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

    public CashClosing getCashClosing() {
        return cashClosing;
    }

    public void setCashClosing(CashClosing cashClosing) {
        this.cashClosing = cashClosing;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CashMovement that = (CashMovement) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
