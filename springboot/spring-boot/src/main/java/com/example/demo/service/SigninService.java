package com.example.demo.service;

import com.example.demo.entity.Signin;
import com.example.demo.entity.Student;
import com.example.demo.repository.SigninRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SigninService {
    private final SigninRepository repository;

    public Signin addSignin(Signin a){
        return  repository.save(a);
    }
    public Iterable<Signin> getAllSignIns(){
        return  repository.findAll();
    }


}
