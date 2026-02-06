package com.extracker.api.controller;

import com.extracker.api.entities.Category;
import com.extracker.api.service.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    protected CategoryController() {}

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getAllCategories() throws ResponseStatusException {
        List<Category> categories = this.categoryService.listAll();
        if (categories.size() > 0) {
            return ResponseEntity.ok(categories);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/categories/id/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable long id) throws EntityNotFoundException {
            Category category = this.categoryService.findById(id);
            if (category != null) {
                return ResponseEntity.ok(category);
            } else {
                return ResponseEntity.notFound().build();
            }
    }

    @GetMapping("/categories/name/{name}")
    public ResponseEntity<Category> getCategoryByName(@PathVariable String name) throws ResponseStatusException {
        try {
            Category category = this.categoryService.findByName(name);
            return ResponseEntity.ok(category);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Couldn't find a specific category", e);
        }
    }

    @PostMapping("/categories")
    public ResponseEntity<Category> postCategory(@RequestBody Category category) throws ResponseStatusException{
        try {
            Category newCategory = this.categoryService.createNewCategory(category);
            return ResponseEntity.ok(newCategory);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Couldn't create a new category", e);
        }
    }

    @PutMapping("/categories/upd/{id}")
    public ResponseEntity<Category> putCategory(@PathVariable long id, @RequestBody Category category) {
        try {
            Category updtCategory = this.categoryService.updateCategory(id, category);
            return ResponseEntity.ok(updtCategory);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Couldn't find category to update", e);
        }
    }

    @DeleteMapping("/categories/del/{id}")
    public ResponseEntity<Category> deleteCategory(@PathVariable long id) {
        try {
            this.categoryService.removeCategory(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Couldn't find any categories", e);
        }
    }
}
