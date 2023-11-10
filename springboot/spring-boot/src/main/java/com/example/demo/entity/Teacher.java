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
public class Teacher {

   // @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    @Column(name = "teacherId")
    private int teacherId;
    private String college ;
    private String name;


}
