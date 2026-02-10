package com.extracker.api.dto;

import com.extracker.api.entities.Category;
import com.extracker.api.entities.Expense;

import java.util.List;

public record CategoryDTO(String name, String importance, List<Expense> expenses) {
    public static CategoryDTO from(Category category) {
        return new CategoryDTO(category.getName(), category.getImportance(), category.getExpenses());
    }
}
