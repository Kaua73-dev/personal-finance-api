package com.example.gestaoFinanceiro.entity.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="categorys")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="name_category", nullable = false, unique = true, length = 1000)
    private String nameCategory;


    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;


}
