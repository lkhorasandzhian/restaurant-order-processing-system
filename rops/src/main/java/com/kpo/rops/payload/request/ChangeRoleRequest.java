package com.kpo.rops.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ChangeRoleRequest {
    @NotBlank
    @Size(min = 3, max = 20, message = "Username size must be in range of [3;20].")
    private String username;

    @NotBlank(message = "Role must have at least 1 symbol.")
    private String role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
