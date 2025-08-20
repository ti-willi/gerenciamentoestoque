package com.tiwilli.gerenciamentoestoque.dto.cash;

import com.tiwilli.gerenciamentoestoque.entities.cash.SaleItem;

public class SaleItemDTO {

    private Long productId;
    private String name;
    private Integer quantity;
    private Double unitPrice;
    private Double subTotal;


    public SaleItemDTO() {
    }

    public SaleItemDTO(Long productId, String name, Integer quantity, Double unitPrice, Double subTotal) {
        this.productId = productId;
        this.name = name;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.subTotal = subTotal;
    }

    public SaleItemDTO(SaleItem entity) {
        productId = entity.getProduct().getId();
        name = entity.getProduct().getName();
        quantity = entity.getQuantity();
        unitPrice = entity.getUnitPrice();
        subTotal = entity.getSubTotal();
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
