package com.example.gestaoFinanceiro.controller;


import com.example.gestaoFinanceiro.dto.request.LoginRequest;
import com.example.gestaoFinanceiro.dto.request.RegisterRequest;
import com.example.gestaoFinanceiro.dto.response.LoginResponse;
import com.example.gestaoFinanceiro.dto.response.RegisterResponse;
import com.example.gestaoFinanceiro.entity.model.User;
import com.example.gestaoFinanceiro.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@Validated @RequestBody RegisterRequest request){

        RegisterResponse response = userService.register(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);

    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Validated @RequestBody LoginRequest request){
        return ResponseEntity.ok(userService.login(request));
    }
}
