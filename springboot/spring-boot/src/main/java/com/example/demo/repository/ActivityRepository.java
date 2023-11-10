package com.example.demo.repository;

import com.example.demo.entity.Activity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//数据库类
@Repository
public interface ActivityRepository extends CrudRepository<Activity, Integer> {

}
