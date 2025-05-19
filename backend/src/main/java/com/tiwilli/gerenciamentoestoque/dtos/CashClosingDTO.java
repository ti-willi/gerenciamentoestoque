package com.tiwilli.gerenciamentoestoque.dtos;

import com.tiwilli.gerenciamentoestoque.entities.CashClosing;
import com.tiwilli.gerenciamentoestoque.entities.CashMovement;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CashClosingDTO {

    private Long id;
    private LocalDate date;
    private Double initialCash;
    private String finalBalance;
    private List<CashMovementDTO> movements = new ArrayList<>();

    public CashClosingDTO() {
    }

    public CashClosingDTO(Long id, LocalDate date, Double initialCash, String finalBalance) {
        this.id = id;
        this.date = date;
        this.initialCash = initialCash;
        this.finalBalance = finalBalance;
    }

    public CashClosingDTO(CashClosing entity) {
        id = entity.getId();
        date = entity.getDate();
        initialCash = entity.getInitialCash();
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getInitialCash() {
        return initialCash;
    }

    public void setInitialCash(Double initialCash) {
        this.initialCash = initialCash;
    }

    public String getFinalBalance() {
        return finalBalance;
    }

    public void setFinalBalance(String finalBalance) {
        this.finalBalance = finalBalance;
    }

    public List<CashMovementDTO> getMovements() {
        return movements;
    }
}
