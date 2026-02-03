package com.extracker.api.service;

import com.extracker.api.entities.Category;
import com.extracker.api.repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository repository) {
        this.categoryRepository = repository;
    }

    private final boolean hasCategories() {
        int count = this.listAll().size();
        return count != 0;
    }

    public Category createNewCategory(Category newCategory) throws EntityNotFoundException {
        if (!hasCategories()) {
            this.categoryRepository.save(newCategory);
            return newCategory;
        } else if (hasCategories()) {
            boolean categoryExists = this.findByName(newCategory.getName()) != null;
            if (!categoryExists) {
                this.categoryRepository.save(newCategory);
                return newCategory;
            } else {
                throw new EntityNotFoundException("Can't create a category that already exists");
            }
        } else {
            throw new EntityNotFoundException("Can't create a new category");
        }
    }

    public Category findById(long id) throws EntityNotFoundException {
        try {
            return this.categoryRepository.findById(id);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Entity was not found");
        }
    }

    public Category findByName(String name) throws EntityNotFoundException{
        try {
            return this.categoryRepository.findByName(name);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Entity was not found");
        }
    }

    public List<Category> listAll() throws EntityNotFoundException {
        try {
            return this.categoryRepository.findAll();
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Can't list any categories");
        }
    }

    public Category removeCategory(long id) throws EntityNotFoundException {
        try {
            Category remCategory = this.findById(id);
            this.categoryRepository.delete(remCategory);
            return remCategory;
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Couldn't find category to delete");
        }
    }

    public Category updateCategory(long id, Category newCategory) throws EntityNotFoundException {
        try {
            Category updCategory = this.findById(id);
            updCategory.setName(newCategory.getName());
            updCategory.setExpenses(newCategory.getExpenses());
            updCategory.setImportance(newCategory.getImportance());
            this.categoryRepository.save(updCategory);

            return updCategory;
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Couldn't find category to delete");
        }
    }
}
