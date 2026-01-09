package com.example.gestaoFinanceiro.controller;


import com.example.gestaoFinanceiro.dto.request.RevenuesRequest;
import com.example.gestaoFinanceiro.dto.response.RevenuesResponse;
import com.example.gestaoFinanceiro.entity.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }



    @PostMapping("/category")
    public ResponseEntity<RevenuesResponse> createCategory(@RequestBody RevenuesRequest request){

        RevenuesResponse response = categoryService.createCategory(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);


    }

    @GetMapping("/all/categorys")
    public List<RevenuesResponse> getAllCategory(){
        return categoryService.getAllCategory();
    }

    @GetMapping("/category/{nameCategory}")
    public RevenuesResponse getCategoryByName(@PathVariable String nameCategory, User user){
        return categoryService.getCategoryByName(nameCategory);
    }


    @DeleteMapping("/category/{nameCategory}")
    public void deleteCategoryByName(@PathVariable String nameCategory, User user){
        categoryService.deleteCategoryByName(nameCategory);
    }


    @PutMapping("/category/{nameCategory}")
        public RevenuesResponse updateCategoryByName(@PathVariable String nameCategory, @RequestBody RevenuesRequest request){
            return categoryService.updateCategoryByName(nameCategory, request);
    }



}
