package com.example.gestaoFinanceiro.entity.repository;

import com.example.gestaoFinanceiro.entity.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Optional<Category> findByNameCategory(String nameCategory);

}
