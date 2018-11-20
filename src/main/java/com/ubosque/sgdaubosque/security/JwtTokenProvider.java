package com.ubosque.sgdaubosque.security;

import com.ubosque.sgdaubosque.payload.RolOrProfile;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.*;

/**
 * Created by rajeevkumarsingh on 19/08/17.
 */

@Component
public class JwtTokenProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

    @Value("${app.jwtSecret}")
    private String jwtSecret;

    @Value("${app.jwtExpirationInMs}")
    private int jwtExpirationInMs;

    public String generateToken(Authentication authentication) {

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        List<Object> permissions =  userPrincipal.getProfiles().stream().map(profile -> profile.getPermissions() )
                .flatMap(x -> x.stream())
                .distinct()
                .collect(Collectors.toList());
        
        List<RolOrProfile> rolesOrProfiles = userPrincipal.getProfiles().stream()
                .map(profile -> new RolOrProfile(profile))
                .collect(Collectors.toList());

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);
              
        Map<String,String> user = new HashMap<>();
        user.put("name", userPrincipal.getName());
        user.put("username", userPrincipal.getUsername());
        user.put("email", userPrincipal.getEmail());

        return Jwts.builder()
        .setSubject(userPrincipal.getId().toString())
        .setIssuedAt(new Date())
        .claim("user", user)
        .claim("roles", rolesOrProfiles)
        .claim("permissions", permissions)
        .setExpiration(expiryDate)
        .signWith(SignatureAlgorithm.HS512, jwtSecret)
        .compact();
    }

    public UUID getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        UUID idUser = UUID.fromString(claims.getSubject());

        return idUser;
    }

    public Claims getUserBody(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        return claims;
    }

    public boolean validateToken(String authToken) throws Exception{
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            logger.error("Invalid JWT signature");
            throw new Exception("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            logger.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            logger.error("Expired JWT token");

        } catch (UnsupportedJwtException ex) {
            logger.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            logger.error("JWT claims string is empty.");
        }
        return false;
    }
}
