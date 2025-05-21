package com.tiwilli.gerenciamentoestoque.entities.cash;

import com.tiwilli.gerenciamentoestoque.entities.enums.MovementType;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "tb_cash_movement")
public class CashMovement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Instant moment;
    private Double amount;
    private String description;
    private MovementType type;

    @ManyToOne
    @JoinColumn(name = "cash_closing_id")
    private CashSession cashClosing;

    public CashMovement() {
    }

    public CashMovement(Long id, Instant moment, Double amount, String description, MovementType type, CashSession cashClosing) {
        this.id = id;
        this.moment = moment;
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

    public CashSession getCashClosing() {
        return cashClosing;
    }

    public void setCashClosing(CashSession cashClosing) {
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
