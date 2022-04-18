package com.tattooshop.shop.service;

import com.tattooshop.shop.model.Favorites;
import com.tattooshop.shop.model.Tattoo;

import java.util.List;

public interface FavoritesService {

    List<Tattoo> listAllTattoosInFavorites(Long favoritesId);
    Favorites getActiveFavorites(String username);
    Favorites addTattooToFavorites(String username, Long tattooId);
}
