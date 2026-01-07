package com.example.gestaoFinanceiro.auth;

import com.example.gestaoFinanceiro.entity.model.User;
import org.springframework.security.core.context.SecurityContextHolder;

public abstract class AuthCategoryService {

    protected User getAuthenticatedUser(){
        return (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }
}
