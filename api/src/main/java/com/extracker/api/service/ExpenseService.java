package com.extracker.api.service;

import com.extracker.api.entities.Category;
import com.extracker.api.entities.Expense;
import com.extracker.api.repository.ExpenseRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {

    @Autowired
    private final ExpenseRepository expenseRepository;
    private final CategoryService categoryService;

    @Autowired
    public ExpenseService(ExpenseRepository expenseRepository, CategoryService categoryService) {
        this.expenseRepository = expenseRepository;
        this.categoryService = categoryService;
    }

    public void createNewExpense(Expense newExpense, long categoryId) {
        this.setCategory(newExpense, categoryId);
        this.expenseRepository.save(newExpense);
    }

    public Expense findExpenseById(long expenseId) {
        return this.expenseRepository.findById(expenseId);
    }

    public List<Expense> findAll() {
        return this.expenseRepository.findAll();
    }

    public void removeExpense(long expenseId) {
        this.expenseRepository.deleteById(expenseId);
    }

    public void updateExpense(long expenseId, Expense newExpense) {
        Expense changingExp = this.findExpenseById(expenseId);
        changingExp.setName(newExpense.getName());
        changingExp.setValue(newExpense.getValue());
        changingExp.setCategory(newExpense.getCategory());
        changingExp.setCreatedAt(newExpense.getCreatedAt());
        this.expenseRepository.save(changingExp);
    }

    public List<Expense> listByCategory(long ctgId) throws EntityNotFoundException {
        try {
            return this.expenseRepository.findByCategory(ctgId);
        } catch (Exception e) {
            throw new EntityNotFoundException(e);
        }
    }

    public void setCategory(Expense expense, long categoryId) throws EntityNotFoundException {
        try {
            Category category = this.categoryService.findById(categoryId);
            expense.setCategory(category);
        } catch (Exception e) {
            throw new EntityNotFoundException(e);
        }
    }
}
