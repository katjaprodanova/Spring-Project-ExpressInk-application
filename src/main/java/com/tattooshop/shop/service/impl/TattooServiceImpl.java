package com.tattooshop.shop.service.impl;

import com.tattooshop.shop.model.Category;
import com.tattooshop.shop.model.Tattoo;
import com.tattooshop.shop.model.User;
import com.tattooshop.shop.repository.TattooRepository;
import com.tattooshop.shop.service.TattooService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TattooServiceImpl implements TattooService {

    private final TattooRepository tattooRepository;

    public TattooServiceImpl(TattooRepository tattooRepository) {
        this.tattooRepository = tattooRepository;
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
        return Optional.empty();
    }

    @Override
    public Optional<Tattoo> save(String name, String description, Double price, Category category, User artist) {
        return Optional.empty();
    }

    @Override
    public Optional<Tattoo> edit(Long id, String name, String description, Double price, Category category, User artist) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Long tattooId) {

    }
}
