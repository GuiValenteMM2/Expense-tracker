package com.extracker.api.controller;

import com.extracker.api.entities.Category;
import com.extracker.api.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CategoryController {

    private CategoryService categoryService;

    protected CategoryController() {}

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/category")
    public List<Category> getAllCategories() {
        return this.categoryService.listAll();
    }

    @GetMapping("/category/{id}")
    public Category getCategory(@PathVariable long id) {
        return this.categoryService.findById(id);
    }

    @PostMapping("/category")
    public void postCategory(@RequestParam Category category) {
        this.categoryService.createNewCategory(category);
    }

    @PutMapping("/category/{id}")
    public void putCategory(@PathVariable long id, @RequestParam Category category) {
        this.categoryService.updateCategory(id, category);
    }

    @DeleteMapping("/category/{id}")
    public void deleteCategory(@PathVariable long id) {
        this.categoryService.removeCategory(id);
    }
}
