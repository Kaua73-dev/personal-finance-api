package com.example.gestaoFinanceiro.entity.repository;

import com.example.gestaoFinanceiro.dto.response.CategoryTotalResponse;
import com.example.gestaoFinanceiro.entity.model.Revenues;
import com.example.gestaoFinanceiro.entity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RevenuesRepository extends JpaRepository<Revenues, Integer> {


    Optional<Revenues> findByDescriptionAndUser(String description, User user);

    List<Revenues> findByUser(User user);

    List<Revenues> findByUserAndDateBetween(User user, LocalDate start, LocalDate end);

    Optional<Revenues> findByUserAndNameCategory(User user, String nameCategory);



    @Query("""
        SELECT COALESCE(SUM(r.value), 0)
        FROM Revenues r 
        WHERE r.user = :user
        AND r.date >= :start
        AND r.date < :end   
""")
    BigDecimal totalRevenuesByUser(
            @Param("user") User user,
            @Param("start") LocalDate start,
            @Param("end") LocalDate end
    );


    @Query("""

        SELECT new com.example.gestaoFinanceiro.dto.response.CategoryTotalResponse(
        r.nameCategory,
        SUM(r.value)
        )
    FROM Revenues r
    WHERE r.user = :user
    AND r.date >= :start
    AND r.date < :end
    GROUP BY r.nameCategory    
""")
    List<CategoryTotalResponse> totalRevenuesByCategory(
            @Param("user") User user,
            @Param("start") LocalDate start,
            @Param("end") LocalDate end
    );

}
