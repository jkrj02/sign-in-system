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
public class Manager {

   // @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    @Column(name = "managerId")
    private int managerId;
    private String college;
    private String name ;

}
