package com.example.demo.repository;

import com.example.demo.entity.Teacher;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//数据库类
@Repository
public interface TeacherRepository extends CrudRepository<Teacher, Integer> {

}
