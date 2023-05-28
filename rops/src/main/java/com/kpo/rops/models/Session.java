package com.kpo.rops.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * A special class for 'SESSION' database.
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "session")
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "session_token")
    private String sessionToken;

    @Column(name = "expires_at")
    private Timestamp expiresAt;

    public Session(Integer userId, String sessionToken) {
        this.userId = userId;
        this.sessionToken = sessionToken;
    }
}
