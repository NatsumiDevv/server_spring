package com.devPn.socialmedias.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;


import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
//@Data
@Table(name="users")
public class User   {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Integer userId;

    @Column(name="email")
    @Email(message = "Please enter email", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    @NotBlank(message = "Email cannot be blank")
    private String email;

    @Size(min=6,message = "Please enter 6 characters more")
    @NotBlank(message = "Password cannot be blank")
    @Column(name="password")
    private String password;


    @NotBlank(message = "Full name cannot be blank")
    @Column(name="full_name")
    private String fullName;

    @Column(name="description")
    private String description;

    @Column(name="address")
    private String address;

    @Pattern(regexp="(^$|[0-9]{10})", message = "Please enter a valid 10-digit phone number")
    @Column(name="phone_number")
    private String phoneNumber;

    @Column(name = "enable", columnDefinition = "boolean default false")
    private boolean enable = false;

    @Column(name = "gender")
    private String gender;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserImage avatarImage;

    @JsonBackReference
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    Set<Post> posts;

    @JsonBackReference
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    Set<Follow> following;

    @JsonBackReference
    @OneToMany(mappedBy = "following",cascade = CascadeType.ALL)
    Set<Follow> followers;


    @ManyToMany(mappedBy = "users",cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Room> rooms;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Notification> notificationList;


    private String token;


}
