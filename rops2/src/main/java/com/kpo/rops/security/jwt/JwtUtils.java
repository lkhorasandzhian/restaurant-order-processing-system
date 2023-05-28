package com.kpo.rops.security.jwt;

import com.kpo.rops.security.services.UserDetailsImpl;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Utility class for JWT token generation, parsing, and validation.
 */
@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${restaurant.app.jwtSecret}")
    private String jwtSecret;

    @Value("${restaurant.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    /**
     * Generates a JWT token based on the provided authentication information.
     @param authentication the Authentication object.
     @return the generated JWT token.
     */
    public String generateJwtToken(Authentication authentication) {

        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    /**
     * Extracts the username from the JWT token.
     @param token the JWT token.
     @return the extracted username.
     */
    public String getUserNameFromJwtToken(String token) {
        return Jwts
                .parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    /**
     * Extracts the expiration date from the JWT token.
     @param token the JWT token.
     @return the expiration date.
     */
    public Date extractExpiration(String token) {
        return Jwts
                .parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
    }

    /**
     * Validates the JWT token.
     @param authToken the JWT token to validate.
     @return true if the token is valid, false otherwise.
     */
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature: { " + e.getMessage() + " }");
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: { " + e.getMessage() + " }");
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: { " + e.getMessage() + " }");
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: { " + e.getMessage() + " }");
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: { " + e.getMessage() + " }");
        }

        return false;
    }
}
