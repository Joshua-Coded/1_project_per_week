package com.alana.expensetracker.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private String id;
    private String userName;
    private String password;
    private String email;

}
