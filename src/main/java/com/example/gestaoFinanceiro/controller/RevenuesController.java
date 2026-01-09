package com.example.gestaoFinanceiro.controller;


import com.example.gestaoFinanceiro.dto.request.RevenuesRequest;
import com.example.gestaoFinanceiro.dto.response.RevenuesResponse;
import com.example.gestaoFinanceiro.entity.model.Revenues;
import com.example.gestaoFinanceiro.service.RevenuesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


    @GetMapping("/revenues")
    public List<RevenuesResponse> getAllRevenues(){
        return revenuesService.getAllRevenues();
    }


    @GetMapping("/revenues/{year}/{month}")
    public List<RevenuesResponse> getRevenuesByDate(@PathVariable int year, @PathVariable int month){
        return revenuesService.getRevenuesRepositoryByDate(year, month);
    }

    @GetMapping("/revenues/{nameCategory}")
    public List<Revenues> getRevenuesByNameCategory(@PathVariable @RequestBody String nameCategory){
        return revenuesService.getRevenuesByNameCategory(nameCategory);
    }


    @DeleteMapping("/revenues/{description}")
    public void deleteRevenuesByDescriptionAndUser(@PathVariable String description){
        revenuesService.deleteRevenuesByDescriptionAndUser(description);
    }


    @PutMapping("/revenues/{description}")
    public RevenuesResponse updateRevenues(@PathVariable String description, @RequestBody RevenuesRequest request){
        return revenuesService.updateRevenues(request, description);
    }


}



