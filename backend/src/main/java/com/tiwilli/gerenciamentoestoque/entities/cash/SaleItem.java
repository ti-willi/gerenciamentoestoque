package com.tiwilli.gerenciamentoestoque.entities.cash;

import com.tiwilli.gerenciamentoestoque.entities.inventory.Product;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tb_sale_item")
public class SaleItem {

    @EmbeddedId
    private SaleItemPK id = new SaleItemPK();

    private Integer quantity;
    private Double unitPrice;

   public SaleItem() {
   }

    public SaleItem(Sale sale, Product product, Integer quantity, Double unitPrice) {
        id.setSale(sale);
        id.setProduct(product);
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public Sale getSale() {
       return id.getSale();
    }

    public void setSale(Sale sale) {
       id.setSale(sale);
    }

    public Product getProduct() {
       return id.getProduct();
    }

    public void setProduct(Product product) {
       id.setProduct(product);
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SaleItem saleItem = (SaleItem) o;

        return Objects.equals(id, saleItem.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
