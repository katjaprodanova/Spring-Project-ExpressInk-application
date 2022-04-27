package com.tattooshop.shop.repository;

import com.tattooshop.shop.model.Category;
import com.tattooshop.shop.model.Tattoo;
import com.tattooshop.shop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TattooRepository extends JpaRepository<Tattoo,Long> {
    Optional<Tattoo> findByName(String name);
    Optional<Tattoo> findByCategory(Category category);
    Optional<Tattoo> findByArtist(User artist);
    void deleteByName(String name);


    //  void deleteById(Long tattooId);
}
