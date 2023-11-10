package com.example.demo.repository;

import com.example.demo.entity.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//数据库类
@Repository
public interface StudentRepository extends CrudRepository<Student, Integer> {

}
// 示例
// User findByUserName(String userName);
//    boolean existsByUserName(String userName);
//    User findByUserNameAndPassword(String userName, String password);
