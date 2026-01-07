package com.example.gestaoFinanceiro.controller;


import com.example.gestaoFinanceiro.dto.request.RevenuesRequest;
import com.example.gestaoFinanceiro.dto.response.RevenuesResponse;
import com.example.gestaoFinanceiro.service.RevenuesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class RevenuesController {


    private final RevenuesService revenuesService;

    public RevenuesController(RevenuesService revenuesService) {
        this.revenuesService = revenuesService;
    }



    @PostMapping("/revenues")
    public ResponseEntity<RevenuesResponse> createRevenues(@RequestBody RevenuesRequest request){

        RevenuesResponse response = revenuesService.createRevenues(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);

    }
}



