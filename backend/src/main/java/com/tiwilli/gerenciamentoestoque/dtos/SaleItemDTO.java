package com.tiwilli.gerenciamentoestoque.dtos;

import com.tiwilli.gerenciamentoestoque.entities.SaleItem;

public class SaleItemDTO {

    private Long id;
    private Integer quantity;
    private Double unitPrice;
    private ProductDTO product;
    private Long saleId;

    public SaleItemDTO() {
    }

    public SaleItemDTO(Long id, Integer quantity, Double unitPrice, ProductDTO product, Long saleId) {
        this.id = id;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.product = product;
        this.saleId = saleId;
    }

    public SaleItemDTO(SaleItem entity) {
        id = entity.getId();
        quantity = entity.getQuantity();
        unitPrice = entity.getUnitPrice();
        product = new ProductDTO(entity.getProduct());
        saleId = entity.getSale().getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public Long getSaleId() {
        return saleId;
    }

    public void setSaleId(Long saleId) {
        this.saleId = saleId;
    }
}
