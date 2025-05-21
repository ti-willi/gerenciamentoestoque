package com.tiwilli.gerenciamentoestoque.repositories.inventory;

import com.tiwilli.gerenciamentoestoque.entities.inventory.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
