package com.tiwilli.gerenciamentoestoque.dto.inventory;

import com.tiwilli.gerenciamentoestoque.entities.inventory.Product;
import com.tiwilli.gerenciamentoestoque.entities.enums.Gender;


public class ProductDTO {

    private Long id;
    private Long productCode;
    private String name;
    private String description;
    private String imgUrl;
    private Gender gender;
    private Double price;
    private Integer quantity;
    private Long categoryId;

    public ProductDTO() {
    }

    public ProductDTO(Long id, Long productCode, String name, String description, String imgUrl, Gender gender, Double price, Integer quantity, Long categoryId) {
        this.id = id;
        this.productCode = productCode;
        this.name = name;
        this.description = description;
        this.imgUrl = imgUrl;
        this.gender = gender;
        this.price = price;
        this.quantity = quantity;
        this.categoryId = categoryId;
    }

    public ProductDTO(Product entity) {
        id = entity.getId();
        productCode = entity.getProductCode();
        name = entity.getName();
        description = entity.getDescription();
        imgUrl = entity.getImgUrl();
        gender = entity.getGender();
        price = entity.getPrice();
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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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
