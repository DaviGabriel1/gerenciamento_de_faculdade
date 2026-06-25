package com.gerenciamentofaculdade.gerenciamento_de_faculdade.iam;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.function.Function;

@Service
public interface JwtService {
    String generateToken(Map<String,Object> claims, UserDetails userDetails);
    String extractUsername(String token);
    <T> T extractClaim(String token, Function<Claims,T> claimResolver);
    boolean isTokenValid(String token, UserDetails userDetails);
}
