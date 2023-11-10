package com.example.demo.service;

import com.example.demo.entity.Manager;
import com.example.demo.repository.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ManagerService {
    private final ManagerRepository repository;


    public Iterable<Manager> getAllManagers(){
        return  repository.findAll();
    }


}
