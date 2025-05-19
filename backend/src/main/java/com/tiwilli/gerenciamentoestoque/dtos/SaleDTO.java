package com.tiwilli.gerenciamentoestoque.dtos;

import com.tiwilli.gerenciamentoestoque.entities.Sale;
import com.tiwilli.gerenciamentoestoque.entities.SaleItem;
import com.tiwilli.gerenciamentoestoque.entities.enums.PaymentType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SaleDTO {

    private Long id;
    private LocalDateTime saleDate;
    private Double total;
    private PaymentType paymentType;
    private List<SaleItemDTO> items = new ArrayList<>();

    public SaleDTO() {
    }

    public SaleDTO(Long id, LocalDateTime saleDate, Double total, PaymentType paymentType) {
        this.id = id;
        this.saleDate = saleDate;
        this.total = total;
        this.paymentType = paymentType;
    }

    public SaleDTO(Sale entity) {
        id = entity.getId();
        saleDate = entity.getSaleDate();
        total = entity.getTotal();
        for (SaleItem item : entity.getItems()) {
            SaleItemDTO itemDTO = new SaleItemDTO(item);
            items.add(itemDTO);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDateTime saleDate) {
        this.saleDate = saleDate;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public List<SaleItemDTO> getItems() {
        return items;
    }

}
