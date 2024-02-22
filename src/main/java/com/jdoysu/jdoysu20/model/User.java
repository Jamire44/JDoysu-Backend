package com.jdoysu.jdoysu20.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity(name = "login")
@Table
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @SequenceGenerator(name = "login_sequence", sequenceName = "login_sequence", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "login_sequence")
    @Column(name = "id")
    private Long id;

//    @Column(name = "first_name", nullable = false)
//    private String firstName;
//
//    @Column(name = "last_name", nullable = false)
//    private String lastName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    public User(String email, String password) {
//        this.firstName = firstName;
//        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
}
