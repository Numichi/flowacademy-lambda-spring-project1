package com.example.demo.controllers;

import com.example.demo.database.Comment;
import com.example.demo.database.CommentRepository;
import com.example.demo.database.UserRepository;
import com.example.demo.model.CommentRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/comment")
public class CommentController {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    CommentController(CommentRepository commentRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    @PostMapping
    public void addComment(@RequestBody CommentRequest comment) {
        var user = userRepository.findByUsername(comment.getUsername())
                .orElseThrow(() -> new NoSuchElementException("User can not write comment coz not exist."));

        var _comment = Comment.builder()
                .user(user)
                .message(comment.getComment())
                .build();

        commentRepository.save(_comment);
    }

    @GetMapping("{id}")
    public List<Comment> addComment(@PathVariable int id) {
        var user = userRepository.findById(id).orElseThrow();
        return user.getCommentList();
    }
}
