package com.example.demo.entity;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public  class returnActivity
{
    @Id
    @Column(name = "Id")
    public int Id;
    public int activityId;
    public int userId;
    public String signin;
    public int organizerId;
    public String type ;
    public String name;
    public String time;
    public String endTime;
    public int classroomId;
    public String approved;
}
