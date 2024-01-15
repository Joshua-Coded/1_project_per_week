package com.alana.expensetracker.controller;

import com.alana.expensetracker.dto.ExpenseDto;
import com.alana.expensetracker.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

@RestController
@RequestMapping("/api/expense")
//@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping
    public ResponseEntity<Void> addExpense(@RequestBody ExpenseDto expenseDto) throws MalformedURLException, URISyntaxException {
        String expenseId = expenseService.addExpense(expenseDto);
        URL location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(expenseId)
                .toUri().toURL();

        return ResponseEntity.created(location.toURI())
                .build();
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateExpense(@RequestBody ExpenseDto expense) {
        expenseService.updateExpense(expense);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ExpenseDto> getAllExpenses() {
        return expenseService.getAllExpenses();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ExpenseDto getExpense(@PathVariable String id){
        return expenseService.getExpense(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteExpense(@PathVariable String id) {
        expenseService.deleteExpense(id);
    }
}
