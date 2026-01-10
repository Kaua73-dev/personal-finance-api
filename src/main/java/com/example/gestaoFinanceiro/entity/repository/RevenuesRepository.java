package com.example.gestaoFinanceiro.entity.repository;

import com.example.gestaoFinanceiro.dto.response.CategoryTotalResponse;
import com.example.gestaoFinanceiro.entity.model.Revenues;
import com.example.gestaoFinanceiro.entity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RevenuesRepository extends JpaRepository<Revenues, Integer> {


    Optional<Revenues> findByDescriptionAndUser(String description, User user);

    List<Revenues> findByUser(User user);

    List<Revenues> findByUserAndDateBetween(User user, LocalDate start, LocalDate end);

    Optional<Revenues> findByUserAndNameCategory(User user, String nameCategory);



    @Query("SELECT COALASCE(SUM(r.value), 0) FROM Revenues r WHERE r.user = :user")
    BigDecimal totalRevenuesByUser(User user);

    @Query("""

        SELECT new com.example.gestaoFinanceiro.dto.response.CatrgoryTotalResponse(
        r.nameCategory,
        SUM(r.value)
        )
    FROM Revenues r
    WHERE r.user = :user
    GROUP BY r.nameCategory    
""")
    List<CategoryTotalResponse> totalRevenuesByCategory(User user);


}
