package com.tiwilli.gerenciamentoestoque.entities.cash;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_cash_closing")
public class CashSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Instant openingTime;
    private Instant closingTime;
    private Double initialBalance;
    private Double finalBalance;

    @OneToMany(mappedBy = "cashClosing")
    private List<CashMovement> movements = new ArrayList<>();

    public CashSession() {
    }

    public CashSession(Long id, Instant openingTime, Instant closingTime, Double initialBalance, Double finalBalance) {
        this.id = id;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.initialBalance = initialBalance;
        this.finalBalance = finalBalance;
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

    public List<CashMovement> getMovements() {
        return movements;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CashSession that = (CashSession) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
