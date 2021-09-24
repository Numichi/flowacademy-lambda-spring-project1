package com.example.demo.database;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "text")
    private String message;

    @Column(columnDefinition = "bigint")
    private Instant time;

    @ManyToOne
    @JoinColumn
    private User user;

//    @ManyToMany
//    @JoinTable(
//            name = "Track_Provider",
//            joinColumns = {@JoinColumn(name = "a_id")},
//            inverseJoinColumns = {@JoinColumn(name = "b_id")}
//    )
//    private List<Many2> manyToManyExample;
}
