package com.extracker.api.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "expense")
public class Expense {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String name;
    @ManyToOne
    @JoinColumn(name="category_id", nullable=false)
    private Category category;
    private double value;
    @CreatedDate
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Date createdAt;

    protected Expense() {}

    public Expense(String name, double value, Category category, Date createdAt) {
        setName(name);
        setValue(value);
        setCreatedAt(createdAt);
        setCategory(category);
    }

    @Override
    public String toString() {
        return "Expense name: ".concat(getName())
                .concat(";\nExpense value: ")
                .concat(String.valueOf(getValue()));
    }

}
