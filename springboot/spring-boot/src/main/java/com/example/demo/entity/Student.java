package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

//实体类
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {

   // @GeneratedValue(strategy=GenerationType.IDENTITY)
   // 如果id要用自增加，就要用这句话
    @Id
    @Column(name = "studentId")
    private int studentId;
    private String grade;
    private String college ;
    private String major;
    private boolean gender;
    private String name;


}
