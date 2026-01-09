package com.example.gestaoFinanceiro.entity.repository;

import com.example.gestaoFinanceiro.entity.model.Expenses;
import com.example.gestaoFinanceiro.entity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ExpensesRepository extends JpaRepository<Expenses, Integer> {



    Optional<Expenses> findByDescriptionAndUser(String description, User user);

    Optional<Expenses> findByNameCategoryAndUser(String nameCategory, User user);

    List<Expenses> findByUserAndDateBetween(User user, LocalDate start, LocalDate end);

    List<Expenses> findByUser(User user);


}
