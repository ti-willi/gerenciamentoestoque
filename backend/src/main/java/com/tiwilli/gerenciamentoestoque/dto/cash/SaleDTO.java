package com.tiwilli.gerenciamentoestoque.dto.cash;

import com.tiwilli.gerenciamentoestoque.entities.cash.Sale;
import com.tiwilli.gerenciamentoestoque.entities.cash.SaleItem;
import com.tiwilli.gerenciamentoestoque.entities.enums.PaymentType;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class SaleDTO {

    private Long id;
    private Instant moment;
    private Double total;
    private PaymentType paymentType;
    private List<SaleItemDTO> items = new ArrayList<>();

    public SaleDTO() {
    }

    public SaleDTO(Long id, Instant moment, Double total, PaymentType paymentType) {
        this.id = id;
        this.moment = moment;
        this.total = total;
        this.paymentType = paymentType;
    }

    public SaleDTO(Sale entity) {
        id = entity.getId();
        moment = entity.getMoment();
        total = entity.getTotal();
        for (SaleItem item : entity.getItems()) {
            SaleItemDTO itemDTO = new SaleItemDTO(item);
            items.add(itemDTO);
        }
        paymentType = entity.getPaymentType();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getmoment() {
        return moment;
    }

    public void setmoment(Instant moment) {
        this.moment = moment;
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
