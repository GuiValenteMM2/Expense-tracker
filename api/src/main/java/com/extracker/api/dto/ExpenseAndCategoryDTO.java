package com.extracker.api.dto;

import com.extracker.api.entities.Category;
import com.extracker.api.entities.Expense;

public record ExpenseAndCategoryDTO(String expenseName, double value, String categoryName, String importance) {

    public static ExpenseAndCategoryDTO from(Expense expense, Category category) {
        return new ExpenseAndCategoryDTO(expense.getName(), expense.getValue(), category.getName(), category.getImportance());
    }

}