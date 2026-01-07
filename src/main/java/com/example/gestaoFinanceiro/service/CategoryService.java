package com.example.gestaoFinanceiro.service;


import com.example.gestaoFinanceiro.Exeptions.CategoryAlreadyExistExeption;
import com.example.gestaoFinanceiro.Exeptions.CategoryNotFoundException;
import com.example.gestaoFinanceiro.auth.AuthCategoryService;
import com.example.gestaoFinanceiro.dto.request.CategoryRequest;
import com.example.gestaoFinanceiro.dto.response.CategoryResponse;
import com.example.gestaoFinanceiro.entity.model.Category;
import com.example.gestaoFinanceiro.entity.model.User;
import com.example.gestaoFinanceiro.entity.repository.CategoryRepository;
import com.example.gestaoFinanceiro.entity.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService extends AuthCategoryService {


    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public CategoryService(CategoryRepository categoryRepository, UserRepository userRepository) {
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }


        public CategoryResponse createCategory(CategoryRequest request){

            User user = getAuthenticatedUser();

            if(categoryRepository.findByNameCategoryAndUser(request.nameCategory(), user).isPresent()){
                throw new CategoryAlreadyExistExeption();
            }


            Category category = new Category();
            category.setNameCategory(request.nameCategory());
            category.setUser(user);

            Category categorySaved = categoryRepository.save(category);

            return new CategoryResponse(
              categorySaved.getNameCategory()
            );
        }


        public List<CategoryResponse> getAllCategory(){

        User user = getAuthenticatedUser();


        return categoryRepository.findByUser(user).stream().map
                (category -> new CategoryResponse(category.getNameCategory()))
                .collect(Collectors.toList());

    }


        public CategoryResponse getCategoryByName(String nameCategory){

            User user = getAuthenticatedUser();


            Category category = categoryRepository.findByNameCategoryAndUser(nameCategory, user).orElseThrow(() ->
                    new CategoryNotFoundException()
            );


            return new CategoryResponse(category.getNameCategory());

        }


        public void deleteCategoryByName(String nameCategory, User user) {

            Category category = categoryRepository.findByNameCategoryAndUser(nameCategory, user).orElseThrow(() ->
                    new CategoryNotFoundException()
        );

        categoryRepository.delete(category);


    }



    public CategoryResponse updateCategoryByName(String nameCategory, User user, CategoryRequest request){

        Category category = categoryRepository.findByNameCategoryAndUser(nameCategory, user).orElseThrow(() ->
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
