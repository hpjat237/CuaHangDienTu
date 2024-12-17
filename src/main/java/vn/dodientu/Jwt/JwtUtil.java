package vn.dodientu.Jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import vn.dodientu.dto.UserDetailsImpl;

import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtil {

    @Value("${secret.key}")
    private String secretKey; // Use a strong key and keep it safe

    @Value("${expiration.ms}")
    private int tokenExpirationMs;


    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(Authentication authentication) {
        UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("roleName", user.getUser().getRole().getName())
                .claim("id", user.getUser().getId())
                .claim("email", user.getUser().getEmail())
                .claim("username", user.getUser().getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpirationMs))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }


    public Boolean validateToken(String token, String email) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(email) && !isTokenExpired(token));
    }
}
