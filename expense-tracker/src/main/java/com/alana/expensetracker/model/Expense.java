package com.alana.expensetracker.model;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Expense {
    private String id;
    private String expenseName;
    private ExpenseCategory expenseCategory;
    private BigDecimal expenseAmount;


}
