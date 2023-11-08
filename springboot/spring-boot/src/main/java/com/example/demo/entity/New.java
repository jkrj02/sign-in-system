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
public class New{

    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    @Column(name = "newId")
    private int newId;
    private int userId;
    private int type;
    private String otherName;

    private String content;
    private int postId;
    private int courseId;
    private String subContent;
    private boolean newRead;
    private int otherId;

}
