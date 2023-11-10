package com.example.demo.repository;

import com.example.demo.entity.Classroom;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//数据库类
@Repository
public interface ClassroomRepository extends CrudRepository<Classroom, Integer> {

}
