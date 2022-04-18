package com.tattooshop.shop.service.impl;

import com.tattooshop.shop.model.Favorites;
import com.tattooshop.shop.model.Tattoo;
import com.tattooshop.shop.model.User;
import com.tattooshop.shop.model.exceptions.MyUniversalException;
import com.tattooshop.shop.model.exceptions.TattooAlreadyInFavorites;
import com.tattooshop.shop.model.exceptions.TattooNotFoundException;
import com.tattooshop.shop.model.exceptions.UserNotFoundException;
import com.tattooshop.shop.repository.FavoritesRepository;
import com.tattooshop.shop.repository.TattooRepository;
import com.tattooshop.shop.repository.UserRepository;
import com.tattooshop.shop.service.FavoritesService;
import com.tattooshop.shop.service.TattooService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FavoritesServiceImpl implements FavoritesService {

    private final FavoritesRepository favoritesRepository;
    private final TattooService tattooService;
    private final UserRepository userRepository;

    public FavoritesServiceImpl(FavoritesRepository favoritesRepository, TattooService tattooService, UserRepository userRepository) {
        this.favoritesRepository = favoritesRepository;
        this.tattooService = tattooService;
        this.userRepository = userRepository;
    }

    @Override
    public List<Tattoo> listAllTattoosInFavorites(Long favoritesId) {
        if(!this.favoritesRepository.findById(favoritesId).isPresent()){
            throw new MyUniversalException();
        }
        return this.favoritesRepository.findById(favoritesId).get().getTattoos();
    }

    @Override
    public Favorites getActiveFavorites(String username) {
        User user = this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));

        return this.favoritesRepository
                .findByUser(user)
                .orElseGet(() -> {
                    Favorites favorite = new Favorites(user);
                    return this.favoritesRepository.save(favorite);
                });
    }

    @Override
    public Favorites addTattooToFavorites(String username, Long tattooId) {
        Favorites activeFavorite = this.getActiveFavorites(username);
        Tattoo tattoo = this.tattooService.findById(tattooId)
                .orElseThrow(() -> new TattooNotFoundException(tattooId));
        if(activeFavorite.getTattoos()
                .stream().filter(i -> i.getId().equals(tattooId))
                .collect(Collectors.toList()).size() > 0)
            throw new TattooAlreadyInFavorites(tattooId, username);
        activeFavorite.getTattoos().add(tattoo);
        return this.favoritesRepository.save(activeFavorite);
    }
}
