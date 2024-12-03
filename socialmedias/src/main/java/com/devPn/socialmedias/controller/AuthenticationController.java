package com.devPn.socialmedias.controller;

import com.devPn.socialmedias.exception.AppException;
import com.devPn.socialmedias.exception.ErrorCode;
import com.devPn.socialmedias.model.ApiResponse;
import com.devPn.socialmedias.model.User;
import com.devPn.socialmedias.model.request.AuthenticateRequest;
import com.devPn.socialmedias.model.request.IntrospectRequest;
import com.devPn.socialmedias.model.request.RegisterRequest;
import com.devPn.socialmedias.model.response.AuthenticateResponse;
import com.devPn.socialmedias.model.response.IntrospectResponse;
import com.devPn.socialmedias.reponsitory.UserRepository;
import com.devPn.socialmedias.service.AuthenticationService;
import com.devPn.socialmedias.service.MailService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AuthenticationController {

    AuthenticationService authenticationService;

    UserRepository repository;

    MailService emailSender;


    @PostMapping("/login")
    public ApiResponse<AuthenticateResponse> authenticate(@RequestBody AuthenticateRequest request){
        var result = authenticationService.authenticate(request);
        return ApiResponse.<AuthenticateResponse>builder()
                .result(result)
                .build();
    }

    @GetMapping("/register/confirm")
    public String confirmUser(@RequestParam("token") String token) {
        log.info(token);
        return authenticationService.confirmToken(token);
    }


    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request){
        if(repository.existsByEmail(request.getEmail()))
            throw  new AppException(ErrorCode.USER_EXIST);
        User user  = new User();
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setFullName(request.getFullName());
        String token = UUID.randomUUID().toString();
        user.setToken(token);
        repository.save(user);
        String link = "http://localhost:8081/auth/register/confirm?token="+ token;
        emailSender.send(request.getEmail(), authenticationService.buildEmail(request.getFullName(), link) );
        return "Please check your email to verify your account.";
    }




    @PostMapping("/validate")
    public ApiResponse<IntrospectResponse> validateToken (@RequestBody IntrospectRequest request){
        var result = authenticationService.validateToken(request);

        return ApiResponse.<IntrospectResponse>builder()
                .result(IntrospectResponse.builder()
                        .valid(result)
                        .build())
                .build();
    }

}
