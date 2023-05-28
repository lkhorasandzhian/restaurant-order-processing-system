package com.kpo.rops.controllers;

import com.kpo.rops.models.ERole;
import com.kpo.rops.models.User;
import com.kpo.rops.payload.request.ChangeRoleRequest;
import com.kpo.rops.payload.response.ErrorCheckResponse;
import com.kpo.rops.payload.response.MessageResponse;
import com.kpo.rops.repository.UserRepository;
import com.kpo.rops.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

/**
 * Controller for role updating.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/change_role")
public class RoleController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    /**
     * PUT-request for role changing.
     * @param jwtToken Required token as authorization.
     * @param changeRoleRequest user data with username and new role in JSON-format.
     * @return HTTP-status of user's request.
     */
    @PutMapping
    public ResponseEntity<?> changeRole(@RequestHeader(name = "Authorization") String jwtToken,
                                        @Valid @RequestBody ChangeRoleRequest changeRoleRequest,
                                        BindingResult bindingResult) {
        var msg = ErrorCheckResponse.getResponseEntity(bindingResult);
        if (msg != null) {
            return msg;
        }

        String tokenPart = jwtToken.substring(7);
        String username = jwtUtils.getUserNameFromJwtToken(tokenPart);
        User user = Objects.requireNonNull(userRepository.findByUsername(username).stream().findFirst().orElse(null));

        ERole userRole = user.getRole();

        if (userRole != ERole.manager) {
            return new ResponseEntity<>("Error: you haven't permissions for this operation.\n" +
                    "Needed role: [Manager]; your role: [" + userRole.toString() + "]", HttpStatus.FORBIDDEN);
        }

        User requestedUser = userRepository.findByUsername(changeRoleRequest.getUsername()).stream().findFirst().orElse(null);
        if (requestedUser == null) {
            return new ResponseEntity<>("Error: user with such login hasn't registered yet.", HttpStatus.NOT_FOUND);
        }

        ERole newRole;
        try {
            newRole = ERole.valueOf(changeRoleRequest.getRole().toLowerCase());
        } catch (IllegalArgumentException e) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: selected role doesn't exist."));
        }

        requestedUser.setRole(newRole);
        requestedUser.setUpdatedAt(new Timestamp(new Date().getTime()));

        userRepository.save(requestedUser);

        return ResponseEntity.ok("Role has been changed!");
    }
}
