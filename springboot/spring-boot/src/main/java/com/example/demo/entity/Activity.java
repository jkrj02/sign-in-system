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
public class Activity {

    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    @Column(name = "activityId")
    private int activityId;
    private int organizerId;
    private String type ;
    private String name;
    private String time;
    private String endTime;
    private int classroomId;
    private String approved;


}
