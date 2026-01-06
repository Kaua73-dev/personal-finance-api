package com.example.gestaoFinanceiro.service;


import com.example.gestaoFinanceiro.Exeptions.UserAlreadyExistsException;
import com.example.gestaoFinanceiro.dto.request.CategoryRequest;
import com.example.gestaoFinanceiro.dto.response.CategoryResponse;
import com.example.gestaoFinanceiro.entity.model.Category;
import com.example.gestaoFinanceiro.entity.model.User;
import com.example.gestaoFinanceiro.entity.repository.CategoryRepository;
import com.example.gestaoFinanceiro.entity.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
                throw new UserAlreadyExistsException();
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


}
