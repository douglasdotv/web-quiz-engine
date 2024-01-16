package br.com.dv.engine.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record UserRegistrationRequest(
        @Email(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
        String email,
        @Size(min = 5)
        String password
) {
}
