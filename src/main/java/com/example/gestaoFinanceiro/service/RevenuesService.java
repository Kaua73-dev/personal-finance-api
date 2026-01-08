package com.example.gestaoFinanceiro.service;


import com.example.gestaoFinanceiro.Exeptions.CategoryAlreadyExistExeption;
import com.example.gestaoFinanceiro.Exeptions.CategoryNotFoundException;
import com.example.gestaoFinanceiro.Exeptions.RevenuesNotFoundException;
import com.example.gestaoFinanceiro.auth.AuthVerifyService;
import com.example.gestaoFinanceiro.dto.request.RevenuesRequest;
import com.example.gestaoFinanceiro.dto.response.RevenuesResponse;
import com.example.gestaoFinanceiro.entity.model.Revenues;
import com.example.gestaoFinanceiro.entity.model.User;
import com.example.gestaoFinanceiro.entity.repository.RevenuesRepository;
import com.example.gestaoFinanceiro.entity.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RevenuesService extends AuthVerifyService {


    private final RevenuesRepository revenuesRepository;
    private final UserRepository userRepository;

    public RevenuesService(RevenuesRepository revenuesRepository, UserRepository userRepository) {
        this.revenuesRepository = revenuesRepository;
        this.userRepository = userRepository;
    }




    public RevenuesResponse createRevenues(RevenuesRequest request){

        User user = getAuthenticatedUser();

        if(revenuesRepository.findByDescriptionAndUser(request.description(), user).isPresent()){
            throw new RevenuesNotFoundException();
        }


        Revenues revenues = new Revenues();
        revenues.setValue(request.value());
        revenues.setDate(request.date());
        revenues.setDescription(request.description());
        revenues.setUser(user);

        Revenues revenuesSaved = revenuesRepository.save(revenues);

        return new RevenuesResponse(
        revenuesSaved.getValue(),
        revenuesSaved.getDate(),
        revenuesSaved.getDescription()

        );


    }


    
    public List<RevenuesResponse> getAllRevenues(){

        User user = getAuthenticatedUser();

        return revenuesRepository.findByUser(user).stream().map
        (revenues -> new RevenuesResponse(revenues.getValue(), revenues.getDate(), revenues.getDescription()))
                .collect(Collectors.toList());


    }
    

    public List<RevenuesResponse> getRevenuesRepositoryByDate(int year, int month){

        User user = getAuthenticatedUser();

        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.plusMonths(1);

        return revenuesRepository.findByUserAndDateBetween(user, start, end)
                .stream()
                .map(revenues -> new RevenuesResponse(revenues.getValue(), revenues.getDate(), revenues.getDescription()))
                .toList();


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


        if(request.description() != null){
            revenues.setDescription(request.description());
        }

        if(request.value() != null){
            revenues.setValue(request.value());
        }

        if(request.date() != null){
            revenues.setDate(request.date());
        }


        revenuesRepository.save(revenues);

        return new RevenuesResponse(
            revenues.getValue(),
            revenues.getDate(),
            revenues.getDescription()

      );
    }


}
















