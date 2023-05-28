package com.kpo.rops.security.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kpo.rops.models.ERole;
import com.kpo.rops.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * Implementation of UserDetails interface for user authentication and authorization.
 */
public class UserDetailsImpl implements UserDetails {
    private static final int serialVersionUID = 1;

    private final Integer id;

    private final String username;

    private final String email;

    @JsonIgnore
    private final String password;

    private final ERole role;

    /**
     * Constructs a new UserDetailsImpl instance with the specified parameters.
     @param id the user ID.
     @param username the username.
     @param email the email.
     @param password the password.
     @param role the user role.
     */
    public UserDetailsImpl(Integer id, String username, String email, String password, ERole role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    /**
     * Builds and returns a UserDetailsImpl instance from a User object.
     @param user the User object.
     @return the UserDetailsImpl instance.
     */
    public static UserDetailsImpl build(User user) {
        return new UserDetailsImpl(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.getRole());
    }

    /**
     * Returns the authorities granted to the user.
     @return the collection of granted authorities.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * Compares this UserDetailsImpl object to the specified object.
     @param o the object to compare.
     @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(id, user.id);
    }
}
