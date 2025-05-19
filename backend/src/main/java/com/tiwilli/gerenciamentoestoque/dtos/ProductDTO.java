package com.tiwilli.gerenciamentoestoque.dtos;

import com.tiwilli.gerenciamentoestoque.entities.Product;
import com.tiwilli.gerenciamentoestoque.entities.enums.Gender;


public class ProductDTO {

    private Long id;
    private Long productCode;
    private String name;
    private String description;
    private Gender gender;
    private Double value;
    private Integer quantity;
    private Long categoryId;

    public ProductDTO() {
    }

    public ProductDTO(Long id, Long productCode, String name, String description, Gender gender, Double value, Integer quantity, Long categoryId) {
        this.id = id;
        this.productCode = productCode;
        this.name = name;
        this.description = description;
        this.gender = gender;
        this.value = value;
        this.quantity = quantity;
        this.categoryId = categoryId;
    }

    public ProductDTO(Product entity) {
        id = entity.getId();
        productCode = entity.getProductCode();
        name = entity.getName();
        description = entity.getDescription();
        gender = entity.getGender();
        value = entity.getValue();
        quantity = entity.getQuantity();
        categoryId = entity.getCategory().getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductCode() {
        return productCode;
    }

    public void setProductCode(Long productCode) {
        this.productCode = productCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
