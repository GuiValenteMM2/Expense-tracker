package com.extracker.api.repository;

import com.extracker.api.entities.Expense;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    @Modifying
    @Transactional
    @Query(value = "SELECT * FROM expense WHERE category_id = :categoryId")
    List<Expense> findByCategory(@Param("categoryId") long categoryId);
    Expense findById(long Id);
    @Query(value = "SELECT * FROM expense WHERE createdAt > :dtStart AND createdAt < :dtEnd ORDER BY createdAt")
    List<Expense> listByDate(Date dtStart, Date dtEnd);
}
