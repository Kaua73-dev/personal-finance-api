package com.example.gestaoFinanceiro.entity.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name="revenues")
public class Revenues {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(name="value", nullable = false)
    private BigDecimal value;


    @Column(name="date", nullable = false)
    private LocalDate date;


    @Column(name="description", nullable = false)
    private String description;


    @ManyToOne
    @JoinColumn(name="category_id", nullable = true)
    private Category category;



    @ManyToOne
    @JoinColumn(name="user_id", nullable = true)
    private User user;

}
