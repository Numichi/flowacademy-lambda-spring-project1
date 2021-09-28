package com.example.demo.model;

import com.example.demo.database.Comment;
import lombok.Data;

import java.time.Instant;

@Data
public class CommentResponse {
    private Integer id;
    private String message;
    private Instant time;
    private UserModel user;

    public static CommentResponse convert(Comment comment) {
        var result = new CommentResponse();
        result.setId(comment.getId());
        result.setMessage(comment.getMessage());
        result.setTime(comment.getTime());

        var user = comment.getUser();
        var model = new UserModel();
        model.setUsername(user.getUsername());
        model.setPasswd(user.getPasswd());

        result.setUser(model);

        return result;
    }
}
