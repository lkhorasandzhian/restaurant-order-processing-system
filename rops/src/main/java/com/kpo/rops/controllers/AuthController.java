package com.kpo.rops.controllers;

import com.kpo.rops.models.ERole;
import com.kpo.rops.models.Session;
import com.kpo.rops.models.User;
import com.kpo.rops.payload.request.LoginRequest;
import com.kpo.rops.payload.request.SignupRequest;
import com.kpo.rops.payload.response.ErrorCheckResponse;
import com.kpo.rops.payload.response.MessageResponse;
import com.kpo.rops.repository.SessionRepository;
import com.kpo.rops.repository.UserRepository;
import com.kpo.rops.security.jwt.JwtUtils;
import com.kpo.rops.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SessionRepository sessionRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest,
                                              BindingResult bindingResult) {
        var msg = ErrorCheckResponse.getResponseEntity(bindingResult);
        if (msg != null) {
            return msg;
        }

        User user = userRepository.findByEmail(loginRequest.getEmail()).stream().findFirst().orElse(null);
        if (user == null) {
            return new ResponseEntity<>("Error: user with such email hasn't registered yet.", HttpStatus.NOT_FOUND);
        }

        String username = user.getUsername();

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String role = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList().get(0);

        Session session = new Session(user.getId(), jwt);

        Timestamp time = new Timestamp(jwtUtils.extractExpiration(jwt).getTime());
        session.setExpiresAt(time);

        sessionRepository.save(session);

        return ResponseEntity.ok(new MessageResponse(
                "User " + username + "[" + role.toUpperCase() + "]" + " logged in successfully! " +
                        "Access token: " + jwt)
        );
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest,
                                          BindingResult bindingResult) {
        var msg = ErrorCheckResponse.getResponseEntity(bindingResult);
        if (msg != null) {
            return msg;
        }

        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: username is already taken."));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: email is already in use."));
        }

        User user = new User(signUpRequest.getUsername(),
                             signUpRequest.getEmail(),
                             encoder.encode(signUpRequest.getPassword()));

        user.setRole(ERole.customer);

        if (!userRepository.existsByRole(ERole.manager)) {
            user.setRole(ERole.manager);
        }

        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
