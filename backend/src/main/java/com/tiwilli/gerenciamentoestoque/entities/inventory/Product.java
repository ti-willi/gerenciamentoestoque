package com.tiwilli.gerenciamentoestoque.entities.inventory;

import com.tiwilli.gerenciamentoestoque.entities.cash.Sale;
import com.tiwilli.gerenciamentoestoque.entities.cash.SaleItem;
import com.tiwilli.gerenciamentoestoque.entities.enums.Gender;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long productCode;
    private String name;
    private String description;
    private String imgUrl;
    private Gender gender;
    private Double price;
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "id.product")
    private List<SaleItem> items = new ArrayList<>();

    public Product() {
    }

    public Product(Long id, Long productCode, String name, String description, String imgUrl, Gender gender, Double price, Integer quantity, Category category) {
        this.id = id;
        this.productCode = productCode;
        this.name = name;
        this.description = description;
        this.imgUrl = imgUrl;
        this.gender = gender;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<SaleItem> getItems() {
        return items;
    }

    public List<Sale> getSales() {
        return items.stream().map(SaleItem::getSale).toList();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
