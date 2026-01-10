package com.example.gestaoFinanceiro.entity.repository;

import com.example.gestaoFinanceiro.dto.response.CategoryTotalResponse;
import com.example.gestaoFinanceiro.entity.model.Expenses;
import com.example.gestaoFinanceiro.entity.model.Revenues;
import com.example.gestaoFinanceiro.entity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ExpensesRepository extends JpaRepository<Expenses, Integer> {



    Optional<Expenses> findByDescriptionAndUser(String description, User user);

    List<Expenses> findByUserAndDateBetween(User user, LocalDate start, LocalDate end);

    List<Expenses> findByUser(User user);

    Optional<Expenses> findByUserAndNameCategory(User user, String nameCategory);


    @Query("""
    SELECT COALESCE(SUM(e.value), 0)
        FROM Expenses e
        WHERE e.user = :user
        AND e.date >= :start
        AND e.date < :end 
""")
    BigDecimal totalExpensesByUser(
            @Param("user") User user,
            @Param("start") LocalDate start,
            @Param("end") LocalDate end
    );

    @Query("""
       SELECT new com.example.gestaoFinanceiro.dto.response.CategoryTotalResponse(
        e.nameCategory,
        SUM(e.value)
        )
    FROM Expenses e
    WHERE e.user = :user
    AND e.date >= :start
    AND e.date < :end
    GROUP BY e.nameCategory   
""")
    List<CategoryTotalResponse> totalExpensesByCategory(
            @Param("user") User user,
            @Param("start") LocalDate start,
            @Param("end") LocalDate end
    );


}
