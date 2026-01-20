package com.extracker.api.service;

import com.extracker.api.entities.Category;
import com.extracker.api.entities.Expense;
import com.extracker.api.repository.ExpenseRepository;
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

    public List<Expense> listByCategory(String ctgName) throws NoSuchObjectException {
        List<Expense> expensesByCtg = this.findAll()
                .stream()
                .filter(exp -> exp.getCategory()
                        .getName()
                        .equals(ctgName))
                        .toList();
        if ((long) expensesByCtg.size() > 0) {
            return expensesByCtg;
        } else {
            throw new NoSuchObjectException("Can't find expenses from the provided category");
        }
    }
}
