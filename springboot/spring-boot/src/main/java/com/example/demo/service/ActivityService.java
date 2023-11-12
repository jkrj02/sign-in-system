package com.example.demo.service;

import com.example.demo.entity.Activity;
import com.example.demo.entity.Student;
import com.example.demo.repository.ActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
@RequestMapping("/")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ActivityService {
    private final ActivityRepository repository;

    public Activity addActivity(Activity a){
        return  repository.save(a);
    }
    public Iterable<Activity>  getAllActivitys(){
        return  repository.findAll();
    }
    public Object findById(int id){
        Optional<Activity> tt=repository.findById(id);
        return  tt;
    }


}
