package com.example.cinema.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class RegistrationForm {
    private String userName;
    private String password;
    private String repassword;
}
