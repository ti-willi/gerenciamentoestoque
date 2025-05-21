package com.tiwilli.gerenciamentoestoque.dto.cash;

import com.tiwilli.gerenciamentoestoque.entities.cash.SaleItem;

public class SaleItemDTO {

    private Long productId;
    private Integer quantity;
    private Double unitPrice;


    public SaleItemDTO() {
    }

    public SaleItemDTO(Long productId, Integer quantity, Double unitPrice) {
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public SaleItemDTO(SaleItem entity) {
        productId = entity.getProduct().getId();
        quantity = entity.getQuantity();
        unitPrice = entity.getUnitPrice();
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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

}
