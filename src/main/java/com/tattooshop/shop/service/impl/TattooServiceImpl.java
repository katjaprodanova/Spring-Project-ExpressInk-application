package com.tattooshop.shop.service.impl;

import com.tattooshop.shop.model.Category;
import com.tattooshop.shop.model.Tattoo;
import com.tattooshop.shop.model.User;
import com.tattooshop.shop.model.exceptions.CategoryNotFoundException;
import com.tattooshop.shop.model.exceptions.TattooNotFoundException;
import com.tattooshop.shop.model.exceptions.UserNotFoundException;
import com.tattooshop.shop.repository.CategoryRepository;
import com.tattooshop.shop.repository.TattooRepository;
import com.tattooshop.shop.repository.UserRepository;
import com.tattooshop.shop.service.TattooService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TattooServiceImpl implements TattooService {

    private final TattooRepository tattooRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public TattooServiceImpl(TattooRepository tattooRepository, CategoryRepository categoryRepository, UserRepository userRepository) {
        this.tattooRepository = tattooRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Tattoo> findAll() {
        return this.tattooRepository.findAll();
    }

    @Override
    public Optional<Tattoo> findById(Long tattooId) {

        return this.tattooRepository.findById(tattooId);
    }

    @Override
    public Optional<Tattoo> findByName(String tattooName) {
        return this.tattooRepository.findByName(tattooName);
    }

    @Override
    @Transactional
    public Optional<Tattoo> save(String name, String description, Double price, Long category, String artist,String imgUrl) {
       Category category1 = this.categoryRepository.findById(category).orElseThrow(()->new CategoryNotFoundException(category));
       User artist1 = this.userRepository.findById(artist).orElseThrow(()->new UserNotFoundException(artist));
        Tattoo tattoo = new Tattoo(name,description,price,category1,artist1,imgUrl);
        return Optional.of(this.tattooRepository.save(tattoo));
    }

    @Override
    @Transactional
    public Optional<Tattoo> edit(Long id, String name, String description, Double price, Long category, String artist,String imgUrl) {
        Tattoo tattoo = tattooRepository.findById(id).orElseThrow(()-> new TattooNotFoundException(id));

        Category category1 = this.categoryRepository.findById(category).orElseThrow(()->new CategoryNotFoundException(category));
        User artist1 = this.userRepository.findById(artist).orElseThrow(()->new UserNotFoundException(artist));

        tattoo.setName(name);
        tattoo.setDescription(description);
        tattoo.setPrice(price);
        tattoo.setCategory(category1);
        tattoo.setArtist(artist1);
        tattoo.setImgUrl(imgUrl);


        return Optional.of(this.tattooRepository.save(tattoo));
    }

    @Override
    public void deleteById(Long tattooId) {

        this.tattooRepository.deleteById(tattooId);

    }
}
