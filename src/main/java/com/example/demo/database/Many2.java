//package com.example.demo.database;
//
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//import java.util.Set;
//
//@Entity
//@Table
//@Data
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//public class Many2 {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//
//    @ManyToMany(mappedBy = "manyToManyExample")
//    private Set<Comment> comments;
//}
