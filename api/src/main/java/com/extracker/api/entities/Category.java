package com.extracker.api.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long Id;
    private String name;
    private String importance;
    @OneToMany(mappedBy = "category")
    private List<Expense> expenses;

    protected Category() {}

    public Category(String name, String importance) {
        setName(name);
        setImportance(importance);
    }

    public void addExpense(Expense expense) {
        this.expenses.add(expense);
        expense.setCategory(this);
    }

    @Override
    public String toString() {
        return "Category: ".concat(getName()).concat(getImportance());
    }
}
