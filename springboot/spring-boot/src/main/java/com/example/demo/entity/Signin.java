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
public class Signin {

    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    @Column(name = "Id")
    private int Id;
    private int activityId;
    private int userId ;
    private String signin;

}
