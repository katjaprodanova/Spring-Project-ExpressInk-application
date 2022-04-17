package com.tattooshop.shop.repository;

import com.tattooshop.shop.model.Favorites;
import com.tattooshop.shop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FavoritesRepository extends JpaRepository<Favorites,Long> {
    //Optional<Favorites> findById(Long id);
    Optional<Favorites> findByUser(User user);
}
