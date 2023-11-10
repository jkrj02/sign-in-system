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
public class Leave {

    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    @Column(name = "Id")
    private int Id;
    private int studentId;
    private boolean type ;
    private String startTime;
    private String endTime;
    private int activityId;


}
