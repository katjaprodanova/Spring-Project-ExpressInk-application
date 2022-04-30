package com.tattooshop.shop.model;

import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Data
@Entity
public class Tattoo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Long id;

    private String name;
    @Column(length = 5000)
    private String imgUrl;

    @Column(length = 4000)
    private String description;

    private Double price;

    @ManyToOne
    private Category category;

    @ManyToOne
    private User artist;

    public Tattoo() {
    }

    public Tattoo(String name, String description, Double price, Category category, User artist,String imgUrl) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.artist = artist;
        this.imgUrl=imgUrl;
    }

    public Tattoo(String name, String description, Double price, Category category, User artist) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.artist = artist;
       // this.imgUrl=imgUrl;
    }
}



