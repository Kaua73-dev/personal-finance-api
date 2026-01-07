package com.example.gestaoFinanceiro.controller;


import com.example.gestaoFinanceiro.dto.request.CategoryRequest;
import com.example.gestaoFinanceiro.dto.response.CategoryResponse;
import com.example.gestaoFinanceiro.service.CategoryService;
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
    public ResponseEntity<CategoryResponse> createCategory(@RequestBody CategoryRequest request){

        CategoryResponse response = categoryService.createCategory(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);


    }

    @GetMapping("/all/categorys")
    public List<CategoryResponse> getAllCategory(){
        return categoryService.getAllCategorys();
    }

    @GetMapping("/category/{nameCategory}")
    public CategoryResponse getCategoryByName(@PathVariable String nameCategory){
        return categoryService.getCategoryByName(nameCategory);
    }


    @DeleteMapping("/category/{nameCategory}")
    public void deleteCategoryByName(@PathVariable String nameCategory){
        categoryService.deleteCategoryByName(nameCategory);
    }



}
