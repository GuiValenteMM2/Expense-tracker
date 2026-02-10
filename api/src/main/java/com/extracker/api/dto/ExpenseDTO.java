package com.extracker.api.dto;

import com.extracker.api.entities.Expense;

public record ExpenseDTO(String name, long id, long categoryId) {

    public static ExpenseDTO from(Expense expense) {
        return new ExpenseDTO(expense.getName(), expense.getId(), expense.getCategory().getId());
    }
}
