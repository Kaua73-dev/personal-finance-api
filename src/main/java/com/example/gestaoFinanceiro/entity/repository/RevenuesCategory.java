package com.example.gestaoFinanceiro.entity.repository;

import com.example.gestaoFinanceiro.entity.model.Revenues;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RevenuesCategory extends JpaRepository<Revenues, Integer> {


    Optional<Revenues> findByDiscriptionAndUser(String discription);

}
