package com.tiwilli.gerenciamentoestoque.entities.cash;

import com.tiwilli.gerenciamentoestoque.entities.inventory.Product;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.util.Objects;

@Embeddable
public class SaleItemPK {

    @ManyToOne
    @JoinColumn(name = "sale_id")
    private Sale sale;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public SaleItemPK() {
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SaleItemPK that = (SaleItemPK) o;

        if (!Objects.equals(sale, that.sale)) return false;
        return Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        int result = sale != null ? sale.hashCode() : 0;
        result = 31 * result + (product != null ? product.hashCode() : 0);
        return result;
    }
}
