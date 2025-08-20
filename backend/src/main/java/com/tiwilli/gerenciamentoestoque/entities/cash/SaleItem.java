package com.tiwilli.gerenciamentoestoque.entities.cash;

import com.tiwilli.gerenciamentoestoque.entities.inventory.Product;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tb_sale_item")
public class SaleItem {

    @EmbeddedId
    private SaleItemPK id = new SaleItemPK();

    private String productName;
    private Integer quantity;
    private Double unitPrice;
    private Double subTotal;

   public SaleItem() {
   }

    public SaleItem(Sale sale, Product product, String productName, Integer quantity, Double unitPrice, Double subTotal) {
        id.setSale(sale);
        id.setProduct(product);
        this.productName = productName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.subTotal = subTotal;
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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
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
