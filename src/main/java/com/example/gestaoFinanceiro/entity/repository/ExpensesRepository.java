package com.example.gestaoFinanceiro.entity.repository;

import com.example.gestaoFinanceiro.entity.model.Expenses;
import com.example.gestaoFinanceiro.entity.model.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ExpensesRepository {



    Optional<Expenses> findByDescriptionAndUser(String description, User user);

    Optional<Expenses> findNameCategoryAndUser(String nameCategory, User user);

    List<Expenses> findByUserAndDateBetween(User user, LocalDate start, LocalDate end);

    List<Expenses> findByUser(User user);


}
