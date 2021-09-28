package com.example.demo.controllers;

import com.example.demo.database.Comment;
import com.example.demo.database.CommentRepository;
import com.example.demo.database.UserRepository;
import com.example.demo.model.CommentRequest;
import com.example.demo.model.CommentResponse;
import com.example.demo.services.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/comment")
public class CommentController {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final CommentService commentService;

    CommentController(
            CommentRepository commentRepository,
            UserRepository userRepository,
            CommentService commentService
    ) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.commentService = commentService;
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
    public List<CommentResponse> getComment(@PathVariable int id) {
        return commentService.getComments(id);
    }
}
