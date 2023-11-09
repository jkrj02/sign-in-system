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
public class Weather {

    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    @Column(name = "studentId")
    private int studentId;
    private String grade;
    private String college ;
    private String major;
    private String gender;
    private String name;


}
