package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    @Column(name = "courseId")
    private int courseId;

    private String courseName;
    private String teacherName;

}
