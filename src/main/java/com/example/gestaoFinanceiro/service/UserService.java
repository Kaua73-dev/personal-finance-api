package com.example.gestaoFinanceiro.service;


import com.example.gestaoFinanceiro.Exeptions.UserAlreadyExistsException;
import com.example.gestaoFinanceiro.Exeptions.UserNotFoundException;
import com.example.gestaoFinanceiro.config.TokenConfig;
import com.example.gestaoFinanceiro.dto.request.LoginRequest;
import com.example.gestaoFinanceiro.dto.request.RegisterRequest;
import com.example.gestaoFinanceiro.dto.response.LoginResponse;
import com.example.gestaoFinanceiro.dto.response.RegisterResponse;
import com.example.gestaoFinanceiro.entity.model.User;
import com.example.gestaoFinanceiro.entity.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenConfig tokenConfig;


    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, TokenConfig tokenConfig) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenConfig = tokenConfig;
    }




    public RegisterResponse register(RegisterRequest request){
        if(userRepository.findByEmail(request.email()).isPresent()){
            throw new UserAlreadyExistsException();
        }

        User user = new User();
        user.setName(request.name());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));

        User userSaved = userRepository.save(user);

        return new RegisterResponse(
          userSaved.getName(),
          userSaved.getEmail()
        );

    }


    public LoginResponse login(LoginRequest request){
        if(userRepository.findByEmail(request.email()).isEmpty()){
            throw new UserNotFoundException();
        }

        UsernamePasswordAuthenticationToken userAndPass = new UsernamePasswordAuthenticationToken(request.email(), request.password());
        Authentication authentication = authenticationManager.authenticate(userAndPass);

        User user = (User) authentication.getPrincipal();
        String token = tokenConfig.generateToken(user);

        return new LoginResponse(token);

    }


}
