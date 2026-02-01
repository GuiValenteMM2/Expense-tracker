package com.extracker.api.controller;

import com.extracker.api.entities.Category;
import com.extracker.api.entities.Expense;
import com.extracker.api.service.CategoryService;
import com.extracker.api.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    protected ExpenseController() {}

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping("/expenses")
    List<Expense> getAllExpenses() {
        return this.expenseService.findAll();
    }

    @GetMapping("/expenses/{category}")
    List<Expense> getByCategory(long categoryId) {
        return this.expenseService.listByCategory(categoryId);
    }

    @GetMapping("/expenses/{id}")
    Expense getExpense(@PathVariable long id) {
        return this.expenseService.findExpenseById(id);
    }

    @PostMapping("/expenses")
    void postExpense(@RequestParam Expense expense, @RequestParam Category category) {
        this.expenseService.createNewExpense(expense, category);
    }

    @PutMapping("/expenses/{id}")
    void putExpense(@PathVariable long id, @RequestParam Expense newExpense) {
        this.expenseService.updateExpense(id, newExpense);
    }

    @DeleteMapping("/expenses/{id}")
    void deleteExpense(@PathVariable long id) {
        this.expenseService.removeExpense(id);
    }
}
