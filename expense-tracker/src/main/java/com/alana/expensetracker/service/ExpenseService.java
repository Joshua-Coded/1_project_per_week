package com.alana.expensetracker.service;


import com.alana.expensetracker.dto.ExpenseDto;
import com.alana.expensetracker.exception.ExpenseNotFoundException;
import com.alana.expensetracker.model.Expense;
import com.alana.expensetracker.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExpenseService {
    @Autowired
    private ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public String addExpense(ExpenseDto expenseDto) {
        Expense expense = mapFromDto(expenseDto);
        return expenseRepository.insert(expense).getId();
    }

    public void updateExpense(ExpenseDto expenseDto) {
        Expense expense = mapFromDto(expenseDto);
        Expense savedExpense = expenseRepository.findById(expenseDto.getId()).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        String.format("Cannot Find Expense by ID %s", expense.getId())));
        savedExpense.setExpenseName(expense.getExpenseName());
        savedExpense.setExpenseCategory(expense.getExpenseCategory());
        savedExpense.setExpenseAmount(expense.getExpenseAmount());
        expenseRepository.save(savedExpense);
    }

    public ExpenseDto getExpense(String id) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new ExpenseNotFoundException(String.format("Cannot Find by ID - %s", id)));
        return mapToDto(expense);
    }

    public List<ExpenseDto> getAllExpenses(){
        return expenseRepository.findAll()
                .stream()
                .map(this::mapToDto).collect(Collectors.toList());
    }

    public void deleteExpense(String id) {
        expenseRepository.deleteById(id);
    }


    private ExpenseDto mapToDto(Expense expense) {
        return ExpenseDto.builder()
                .id(expense.getId())
                .expenseName(expense.getExpenseName())
                .expenseCategory(expense.getExpenseCategory())
                .expenseAmount(expense.getExpenseAmount())
                .build();
    }

    private Expense mapFromDto(ExpenseDto expense){
        return Expense.builder()
                .expenseName(expense.getExpenseName())
                .expenseCategory(expense.getExpenseCategory())
                .expenseAmount(expense.getExpenseAmount())
                .build();
    }
}
