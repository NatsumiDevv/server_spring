package com.devPn.socialmedias.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
@Table(name ="likes")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;


    @NotNull

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @NotNull
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
}
