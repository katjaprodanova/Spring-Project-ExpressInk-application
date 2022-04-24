package com.tattooshop.shop.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Favorites {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToMany
    private List<Tattoo> tattoos;

    public Favorites(){

    }

    public Favorites(User user) {
        this.user = user;
        this.tattoos = new ArrayList<>();
    }


}
