package com.extracker.api.service;

import com.extracker.api.entities.Category;
import com.extracker.api.entities.Expense;
import com.extracker.api.exceptions.ExistsAlreadyException;
import com.extracker.api.repository.CategoryRepository;
import com.extracker.api.repository.ExpenseRepository;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.rmi.NoSuchObjectException;
import java.util.List;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository repository) {
        this.expenseRepository = repository;
    }

    public Expense createNewExpense(Expense newExpense) {
        this.expenseRepository.save(newExpense);
        return newExpense;
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

    public Expense updateExpense(long expenseId, Expense newExpense) {
        Expense changingExp = this.findExpenseById(expenseId);
        changingExp.setName(newExpense.getName());
        changingExp.setValue(newExpense.getValue());
        changingExp.setCategory(newExpense.getCategory());
        changingExp.setCreatedAt(newExpense.getCreatedAt());
        this.expenseRepository.save(changingExp);
        return changingExp;
    }

    public List<Expense> listByCategory(String ctgName) throws EntityNotFoundException {
        try {
            String newName = ctgName.toLowerCase();
            return this.expenseRepository.findByCategoryName(newName);
        } catch (Exception e) {
            throw new EntityNotFoundException(e);
        }
    }

    public void setCategory(Category category, long expenseId) throws EntityNotFoundException {
        try {
            Expense expense = this.expenseRepository.findById(expenseId);
            expense.setCategory(category);
            this.expenseRepository.save(expense);
        } catch (Exception e) {
            throw new EntityNotFoundException(e);
        }
    }
}
