package com.example.gestaoFinanceiro.entity.repository;

import com.example.gestaoFinanceiro.entity.model.Revenues;
import com.example.gestaoFinanceiro.entity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RevenuesRepository extends JpaRepository<Revenues, Integer> {


    Optional<Revenues> findByDescriptionAndUser(String description, User user);

    List<Revenues> findByUser(User user);
}
