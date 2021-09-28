package com.example.demo.services;

import com.example.demo.database.UserRepository;
import com.example.demo.model.CommentResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private final UserRepository userRepository;

    CommentService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<CommentResponse> getComments(int userId) {
        var user = userRepository.findById(userId).orElseThrow();

        return user.getCommentList()
                .stream()
                .map(CommentResponse::convert)
                .collect(Collectors.toList());
    }
}
