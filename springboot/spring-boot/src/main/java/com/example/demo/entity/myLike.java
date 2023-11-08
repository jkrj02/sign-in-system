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
public class myLike {

    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    @Column(name = "likeId")
    private int likeId;//like对象的编号
    @Column(name = "user_id")
    private int userId;//用户id 是谁点赞的
    @Column(name = "post_id")
    private int postId;//如果点赞的是帖子，那么帖子id就不为0
    @Column(name = "comment_id")
    private int commentId;//如果点赞评论，评论id不为0
    @Column(name = "course_comment_id")
    private int courseCommentId;//课程评论
    private String info;

}
