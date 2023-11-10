package com.example.demo.service;

import com.example.demo.entity.Leaveinfo;
import com.example.demo.repository.LeaveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LeaveService {
    private final LeaveRepository repository;


    public Iterable<Leaveinfo> getAllLeaves(){
        return  repository.findAll();
    }
    public Leaveinfo addLeave(Leaveinfo a){
        return  repository.save(a);
    }

}
