package com.example.demo.service;

import com.example.demo.entity.student;
import com.example.demo.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class studentService {
    private final StudentRepository repository;


    public Iterable<student> getAllStudents(){
        return  repository.findAll();
    }


}
