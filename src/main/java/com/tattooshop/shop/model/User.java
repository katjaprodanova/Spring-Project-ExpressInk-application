package com.tattooshop.shop.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    private String username;

    private String password;

    private String name;

    private String surname;

    @OneToMany(mappedBy = "artist", fetch = FetchType.EAGER)
    private List<Tattoo> tattoosMade;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Favorites> favorites;

    @Enumerated(value = EnumType.STRING)
    private Role role;


    public User() {
    }


    public User(String username, String password, String name, String surname, Role role) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.role = role;
        this.tattoosMade = new ArrayList<>();
        this.favorites = new ArrayList<>();
    }
}
