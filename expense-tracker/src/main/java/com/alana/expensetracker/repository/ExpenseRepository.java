package com.alana.expensetracker.repository;

import com.alana.expensetracker.model.Expense;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import java.util.Optional;
public interface ExpenseRepository extends MongoRepository<Expense, String> {
}
