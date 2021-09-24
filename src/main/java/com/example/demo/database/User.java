package com.example.demo.database;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 1 -> 5

    @Column(unique = true, nullable = false)
    private String username;

    @Column(columnDefinition = "tinytext", nullable = false)
    private String passwd;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Comment> commentList;
}
