package com.example.demo.service;

import com.example.demo.entity.Classroom;
import com.example.demo.repository.ClassroomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ClassroomService {
    private final ClassroomRepository repository;


    public Iterable<Classroom> getAllClassrooms(){
        return  repository.findAll();
    }


}
