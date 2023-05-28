package com.kpo.rops.payload.response;

import lombok.Data;

/**
 * Full user info with JWT-token checker.
 * Useful for testing responses.
 */
@Data
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Integer id;
    private String username;
    private String email;
    private String role;

    public JwtResponse(String accessToken, Integer id, String username, String email, String role) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
    }
}
