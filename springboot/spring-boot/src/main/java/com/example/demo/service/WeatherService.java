package com.example.demo.service;

import com.example.demo.entity.Weather;
import com.example.demo.repository.WeatherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WeatherService {
    private final WeatherRepository repository;


    public Iterable<Weather> getAllWeathers(){
        return  repository.findAll();
    }


}
