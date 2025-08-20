package com.tiwilli.gerenciamentoestoque.entities.cash;

import com.tiwilli.gerenciamentoestoque.entities.inventory.Product;
import com.tiwilli.gerenciamentoestoque.entities.enums.PaymentType;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_sale")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Instant moment;
    private Double total;
    private PaymentType paymentType;

    @OneToMany(mappedBy = "id.sale")
    private List<SaleItem> items = new ArrayList<>();

    public Sale() {
    }

    public Sale(Long id, Instant moment, Double total, PaymentType paymentType) {
        this.id = id;
        this.moment = moment;
        this.total = total;
        this.paymentType = paymentType;
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

    public void setMoment(Instant moment) {
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

    public List<SaleItem> getItems() {
        return items;
    }

    public List<Product> getProducts() {
        return items.stream().map(SaleItem::getProduct).toList();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sale sale = (Sale) o;

        return Objects.equals(id, sale.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
