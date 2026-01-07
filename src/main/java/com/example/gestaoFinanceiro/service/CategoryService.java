package com.example.gestaoFinanceiro.service;


import com.example.gestaoFinanceiro.Exeptions.CategoryAlreadyExistExeption;
import com.example.gestaoFinanceiro.Exeptions.CategoryNotFoundException;
import com.example.gestaoFinanceiro.dto.request.CategoryRequest;
import com.example.gestaoFinanceiro.dto.response.CategoryResponse;
import com.example.gestaoFinanceiro.entity.model.Category;
import com.example.gestaoFinanceiro.entity.model.User;
import com.example.gestaoFinanceiro.entity.repository.CategoryRepository;
import com.example.gestaoFinanceiro.entity.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {


    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public CategoryService(CategoryRepository categoryRepository, UserRepository userRepository) {
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }



        public CategoryResponse createCategory(CategoryRequest request){
            if(categoryRepository.findByNameCategory(request.nameCategory()).isPresent()){
                throw new CategoryAlreadyExistExeption();
            }

            User user = userRepository.findById(request.user_id()).orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found")
                    );

            Category category = new Category();
            category.setNameCategory(request.nameCategory());
            category.setUser(user);

            Category categorySaved = categoryRepository.save(category);

            return new CategoryResponse(
              categorySaved.getNameCategory()
            );
        }


        public List<CategoryResponse> getAllCategorys(){

        return categoryRepository.findAll().stream().map
                (category -> new CategoryResponse(category.getNameCategory()))
                .collect(Collectors.toList());

    }


        public CategoryResponse getCategoryByName(String nameCategory){

            Category category = categoryRepository.findByNameCategory(nameCategory).orElseThrow(() ->
                    new CategoryNotFoundException()
                    );


            return new CategoryResponse(category.getNameCategory());

        }


        public void deleteCategoryByName(String nameCategory) {

            Category category = categoryRepository.findByNameCategory(nameCategory).orElseThrow(() ->
                    new CategoryNotFoundException()
        );

        categoryRepository.delete(category);


    }



    public CategoryResponse updateCategoryByName(String nameCategory, CategoryRequest request){

        Category category = categoryRepository.findByNameCategory(nameCategory).orElseThrow(() ->
                new CategoryNotFoundException()
        );


        if(request.nameCategory() != null){
            category.setNameCategory(request.nameCategory());
        }

         categoryRepository.save(category);

        return new CategoryResponse(
            category.getNameCategory()
        );

    }




}
