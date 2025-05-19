package com.tiwilli.gerenciamentoestoque.repositories;

import com.tiwilli.gerenciamentoestoque.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
