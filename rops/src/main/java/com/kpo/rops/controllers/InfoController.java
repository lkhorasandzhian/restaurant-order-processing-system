package com.kpo.rops.controllers;

import com.kpo.rops.models.User;
import com.kpo.rops.repository.UserRepository;
import com.kpo.rops.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * Controller for user information.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/info")
public class InfoController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    /**
     * GET-request for info.
     * Check last logged-in user info: username, email, id, role.
     * @param jwtToken Required token as authorization.
     * @return HTTP-status of user's request.
     */
    @GetMapping
    public ResponseEntity<?> getUserInfo(@RequestHeader(name = "Authorization") String jwtToken) {
        StringBuilder msgInfo = new StringBuilder("User info:\n");

        String tokenPart = jwtToken.substring(7);
        String username = jwtUtils.getUserNameFromJwtToken(tokenPart);
        User user = Objects.requireNonNull(userRepository.findByUsername(username).stream().findFirst().orElse(null));

        msgInfo.append("Username: ").append(username).append("\n");
        msgInfo.append("Email: ").append(user.getEmail()).append("\n");
        msgInfo.append("Id: ").append(user.getId()).append("\n");
        msgInfo.append("Role: ").append(user.getRole().toString()).append("\n");

        return ResponseEntity.ok(msgInfo);
    }
}
