package com.ex.sql.sqlex.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateStudentRequest(
        long id,
        @NotBlank(message = "email is required")
        @Size(max = 255, message = "email must not exceed 100 characters")
        String email
) {
}
