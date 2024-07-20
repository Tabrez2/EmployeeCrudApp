package com.tab.EmployeeCrudApp.models;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {

    private int id;

    @NotEmpty(message = "Name is required")
    @Size(min = 1, max = 100, message = "Name must be between 1 and 100 characters")
    private String name;

    @NotEmpty(message = "Email is required")
    @Email(message = "Email should be valid")
    @Size(max = 100, message = "Email must be less than or equal to 100 characters")
    private String email;

    @NotEmpty(message = "Address is required")
    @Size(min = 1, max = 200, message = "Address must be between 1 and 200 characters")
    private String address;

    @NotEmpty(message = "Phone number is required")
    @Pattern(regexp = "\\d{10}", message = "Phone number must be exactly 10 digits")
    private String phone;
}
