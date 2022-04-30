package com.tattooshop.shop.service;

import com.tattooshop.shop.model.Category;
import com.tattooshop.shop.model.Tattoo;
import com.tattooshop.shop.model.User;

import java.util.List;
import java.util.Optional;

public interface TattooService {

    List<Tattoo> findAll();

    Optional<Tattoo> findById(Long tattooId);

    Optional<Tattoo> findByName(String tattooName);

    Optional<Tattoo> save(String name, String description, Double price, Long category, String artist,String imgUrl);

    Optional<Tattoo> edit(Long id, String name, String description, Double price, Long category, String artist,String imgUrl);

    void deleteById(Long tattooId);
}
