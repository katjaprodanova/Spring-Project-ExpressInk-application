package com.tattooshop.shop.service.impl;

import com.tattooshop.shop.model.Category;
import com.tattooshop.shop.model.exceptions.CategoryNotFoundException;
import com.tattooshop.shop.model.exceptions.EmptyFieldException;
import com.tattooshop.shop.repository.CategoryRepository;
import com.tattooshop.shop.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category create(String name) {
        if (name.isEmpty()) throw new EmptyFieldException();
        Category category = new Category(name);
        this.categoryRepository.save(category);
        return category;
    }

    @Override
    public Category edit(Long id,String nameForEdit) {
        Category category = this.categoryRepository.findById(id).orElseThrow(()-> new CategoryNotFoundException(id));
        category.setName(nameForEdit);
        return category;
    }

    @Override
    public void delete(String name) {
        this.categoryRepository.deleteByName(name);
    }

    @Override
    public List<Category> listCategories() {
        return this.categoryRepository.findAll();
    }

    @Override
    public List<Category> searchCategories(String searchText) {
        return this.categoryRepository.findAllByNameLike(searchText);
    }
}
