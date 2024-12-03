package com.devPn.socialmedias.model;


import jakarta.persistence.*;
import lombok.*;



import java.util.Set;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name= "name")
    private String name;

    @ManyToMany(cascade = CascadeType.REMOVE )
    @JoinTable(
            name = "room_users",
            joinColumns = @JoinColumn(name = "room_id" ),
            inverseJoinColumns = @JoinColumn(name="user_id")

    )
    private Set<User> users;

}
