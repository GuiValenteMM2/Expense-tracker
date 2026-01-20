package com.extracker.api.repository;

import com.extracker.api.entities.Expense;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    @Modifying
    @Transactional
    @Query(value = "SELECT * FROM expenses a INNER JOIN category b ON a.category_id = b.id WHERE b.name = :categoryName")
    List<Expense> findByCategoryName(@Param("categoryName") String categoryName);
    Expense findById(long Id);
}
