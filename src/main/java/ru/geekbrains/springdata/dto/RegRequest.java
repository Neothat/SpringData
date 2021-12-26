package ru.geekbrains.springdata.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegRequest {
    private String username;
    private String email;
    private String password;
    private String confirmPassword;
}
