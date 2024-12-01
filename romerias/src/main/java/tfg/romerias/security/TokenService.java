package tfg.romerias.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Optional;

@Service
public class TokenService {

    private final SecretKey secretKey;
    private static final long EXPIRATION_TIME = 3600000;

    public TokenService() {
        this.secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512); // Clave segura de 512 bits
    }

    // Método para generar token con rol
    public String generateToken(String username, String role) {
        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .setIssuedAt(new Date()) // Fecha de creación del token
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // Fecha de expiración
                .signWith(secretKey)
                .compact();
    }

    // Extraer los claims del JWT
    public Optional<Claims> extractClaims(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return Optional.of(claims);
        } catch (Exception e) {
            return Optional.empty(); // Token no válido
        }
    }

    // Validar el token JWT
    public boolean validateToken(String token, UserDetails userDetails) {
        return extractClaims(token)
                .map(claims -> {
                    String username = claims.getSubject();
                    Date expiration = claims.getExpiration();
                    return username.equals(userDetails.getUsername())&& expiration.after(new Date());
                    //claims.getSubject().equals(userDetails.getUsername())
                })
                .orElse(false);
    }
}
