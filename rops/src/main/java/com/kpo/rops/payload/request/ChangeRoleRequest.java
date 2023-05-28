package com.kpo.rops.payload.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class ChangeRoleRequest {
    @NotBlank
    @Size(min = 3, max = 20, message = "Username size must be in range of [3;20].")
    private String username;

    @NotBlank(message = "Role must have at least 1 symbol.")
    private String role;
}
