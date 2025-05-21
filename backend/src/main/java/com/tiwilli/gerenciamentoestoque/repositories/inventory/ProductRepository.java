package com.tiwilli.gerenciamentoestoque.repositories.inventory;

import com.tiwilli.gerenciamentoestoque.entities.inventory.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
