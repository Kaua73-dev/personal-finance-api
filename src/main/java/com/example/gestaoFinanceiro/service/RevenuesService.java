package com.example.gestaoFinanceiro.service;


import com.example.gestaoFinanceiro.Exeptions.CategoryAlreadyExistExeption;
import com.example.gestaoFinanceiro.auth.AuthVerifyService;
import com.example.gestaoFinanceiro.dto.request.RevenuesRequest;
import com.example.gestaoFinanceiro.dto.response.RevenuesResponse;
import com.example.gestaoFinanceiro.entity.model.Revenues;
import com.example.gestaoFinanceiro.entity.model.User;
import com.example.gestaoFinanceiro.entity.repository.RevenuesRepository;
import com.example.gestaoFinanceiro.entity.repository.UserRepository;
import org.springframework.stereotype.Service;

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
            throw new CategoryAlreadyExistExeption();
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
    

}













