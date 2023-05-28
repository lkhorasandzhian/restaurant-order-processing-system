package com.kpo.rops.payload.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class LoginRequest {
    @NotBlank
    @Email(message = "Email must have '@'-symbol.")
    @NotEmpty(message = "Email name must have at least 1 symbol.")
    @Size(max = 50, message = "Email must have no more than 50 symbols.")
    private String email;

    @NotBlank
    @Size(min = 6, max = 40, message = "Password size must be in range of [6;40].")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
