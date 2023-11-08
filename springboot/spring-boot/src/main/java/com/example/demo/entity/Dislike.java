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
public class Dislike {

    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    @Column(name = "dislikeId")
    //private int id;
    private int dislikeId ;
    private int userId ;
    private int postId ;
    private int commentId ;
    private int courseCommentId ;
    //private String courseName;
    //private String tearchName;

}
