package com.example.demo.service;

import com.example.demo.entity.SignIn;
import com.example.demo.repository.SignInRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SignInService {
    private final SignInRepository repository;


    public Iterable<SignIn> getAllSignIns(){
        return  repository.findAll();
    }


}
