package com.tattooshop.shop.repository;

import com.tattooshop.shop.model.Favorites;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoritesRepository extends JpaRepository<Favorites,Long> {
}
