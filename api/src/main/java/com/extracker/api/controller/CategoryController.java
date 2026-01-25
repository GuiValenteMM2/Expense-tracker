package com.extracker.api.controller;

import com.extracker.api.service.CategoryService;
import org.springframework.stereotype.Controller;

@Controller
public class CategoryController {

    private CategoryService categoryService;

    protected CategoryController() {}
}
