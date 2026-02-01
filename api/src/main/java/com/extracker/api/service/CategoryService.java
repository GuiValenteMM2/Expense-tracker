package com.extracker.api.service;

import com.extracker.api.entities.Category;
import com.extracker.api.exceptions.ExistsAlreadyException;
import com.extracker.api.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository repository) {
        this.categoryRepository = repository;
    }

    public Category createNewCategory(Category newCategory) {
        boolean ctgExists = this.listAll().stream().map(cat -> cat.getName().equals(newCategory.getName())).findAny().isPresent();
        if (ctgExists) {
            return this.findById(newCategory.getId());
        }
        return this.categoryRepository.save(newCategory);
    }

    public Category findById(long id) {
        return this.categoryRepository.findById(id);
    }

    public Category findByName(String name) {
        return this.categoryRepository.findByName(name);
    }

    public List<Category> listAll() {
        return this.categoryRepository.findAll();
    }

    public Category removeCategory(long id) {
        Category remCategory = this.findById(id);
        this.categoryRepository.delete(remCategory);
        return remCategory;
    }

    public Category updateCategory(long id, Category newCategory) {
        Category updCategory = this.findById(id);
        updCategory.setName(newCategory.getName());
        updCategory.setExpenses(newCategory.getExpenses());
        updCategory.setImportance(newCategory.getImportance());
        this.categoryRepository.save(updCategory);

        return updCategory;
    }
}
