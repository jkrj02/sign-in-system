package com.example.demo.controller;

import com.example.demo.entity.*;

import com.example.demo.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

//主程序
@RestController
@RequestMapping("/")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class Controller {
    private final studentService service;


    @PersistenceContext
    private EntityManager entityManager;



    @GetMapping("student")
    public Object stu()
    {
        return service.getAllStudents();
    }

    @GetMapping("test4")//复杂逻辑要用sql,不能自动生成
    public Object test4(){
        String sql ="SELECT * FROM my_like";
        //String sql = "SELECT * FROM course INNER JOIN user ON course.teacher_name = user.user_name";
        Query query = entityManager.createNativeQuery(sql, myLike.class);//指定返回类型
        List<myLike> courses = query.getResultList();
        String a="ss";
        return courses;
    }

}
