package com.extracker.api.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long Id;
    private String name;
    private String importance;
    @OneToMany(mappedBy = "category")
    private List<Expense> expenses;
    @CreatedDate
    private Date createdAt;

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
