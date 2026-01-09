package com.example.gestaoFinanceiro.service;


import com.example.gestaoFinanceiro.Exeptions.category.CategoryAlreadyExistException;
import com.example.gestaoFinanceiro.Exeptions.category.CategoryNotFoundException;
import com.example.gestaoFinanceiro.Exeptions.revenues.RevenuesAlreadyExistException;
import com.example.gestaoFinanceiro.Exeptions.revenues.RevenuesNotFoundException;
import com.example.gestaoFinanceiro.auth.AuthVerifyService;
import com.example.gestaoFinanceiro.dto.request.RevenuesRequest;
import com.example.gestaoFinanceiro.dto.response.RevenuesResponse;
import com.example.gestaoFinanceiro.entity.model.Revenues;
import com.example.gestaoFinanceiro.entity.model.User;
import com.example.gestaoFinanceiro.entity.repository.RevenuesRepository;
import com.example.gestaoFinanceiro.entity.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class RevenuesService extends AuthVerifyService {


    private final RevenuesRepository revenuesRepository;

    public RevenuesService(RevenuesRepository revenuesRepository) {
        this.revenuesRepository = revenuesRepository;
    }

    private RevenuesResponse toResponse(Revenues r){
        return new RevenuesResponse(
                r.getNameCategory(),
                r.getValue(),
                r.getDate(),
                r.getDescription()
        );

    }




    public RevenuesResponse createRevenues(RevenuesRequest request){

        User user = getAuthenticatedUser();

        if(revenuesRepository.findByDescriptionAndUser(request.description(), user).isPresent()){
            throw new RevenuesAlreadyExistException();
        }

        if(revenuesRepository.findByUserAndNameCategory(user, request.nameCategory()).isPresent()){
            throw new CategoryAlreadyExistException();
        }

        Revenues revenues = new Revenues();
        revenues.setValue(request.value());
        revenues.setDate(request.date());
        revenues.setDescription(request.description());
        revenues.setNameCategory(request.nameCategory());
        revenues.setUser(user);

        Revenues revenuesSaved = revenuesRepository.save(revenues);

        return new RevenuesResponse(
                revenuesSaved.getNameCategory(),
                revenuesSaved.getValue(),
                revenuesSaved.getDate(),
                revenuesSaved.getDescription()
                );


    }

    public List<RevenuesResponse> getAllRevenues(){
        User user = getAuthenticatedUser();
        return revenuesRepository.findByUser(user).stream().map(this::toResponse).toList();

    }

    public List<RevenuesResponse> getRevenuesRepositoryByDate(int year, int month){

        User user = getAuthenticatedUser();

        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.plusMonths(1);

        return revenuesRepository.findByUserAndDateBetween(user, start, end)
                .stream()
                .map(this::toResponse).toList();

    }

    public Optional<Revenues> getRevenuesByNameCategory(String nameCategory){

        User user = getAuthenticatedUser();
        
        Optional<Revenues> revenues = revenuesRepository.findByUserAndNameCategory(user, nameCategory);


        if(revenues.isEmpty()){
            throw new CategoryNotFoundException();
        }

        return revenues;
    }

    public void deleteRevenuesByDescriptionAndUser(String description){
        User user = getAuthenticatedUser();

        Revenues revenues = revenuesRepository.findByDescriptionAndUser(description, user).orElseThrow(() ->
                new RevenuesNotFoundException()
        );


        revenuesRepository.delete(revenues);

    }

    public RevenuesResponse updateRevenues(RevenuesRequest request, String description){

        User user = getAuthenticatedUser();

        Revenues revenues = revenuesRepository.findByDescriptionAndUser(description, user).orElseThrow(() ->
                new RevenuesNotFoundException()
                );

        if(request.nameCategory() != null){
            revenues.setNameCategory(request.nameCategory());
        }

        if(request.value() != null){
            revenues.setValue(request.value());
        }

        if(request.date() != null){
            revenues.setDate(request.date());
        }

        if(request.description() != null){
            revenues.setDescription(request.description());
        }

        revenuesRepository.save(revenues);

        return new RevenuesResponse(
            revenues.getNameCategory(),
            revenues.getValue(),
            revenues.getDate(),
            revenues.getDescription()
      );
    }

}
















