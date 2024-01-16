    package com.example.Category.config;

    import io.jsonwebtoken.Claims;
    import io.jsonwebtoken.Jwts;
    import io.jsonwebtoken.SignatureAlgorithm;
    import io.jsonwebtoken.io.Decoders;
    import io.jsonwebtoken.security.Keys;
    import lombok.RequiredArgsConstructor;
    import lombok.extern.slf4j.Slf4j;
    import org.springframework.beans.factory.annotation.Value;
    import org.springframework.security.core.userdetails.UserDetails;
    import org.springframework.stereotype.Service;

    import java.security.Key;
    import java.util.Date;
    import java.util.HashMap;
    import java.util.Map;
    import java.util.function.Function;
    @Slf4j
    @Service
    @RequiredArgsConstructor
    public class JwtService {
        @Value("${spring.application.jwt.secret-key}")
        private String secretKey;
        @Value("${spring.application.jwt.expiration}")
        private long jwtExpiration;

        @Value("${spring.application.jwt.refresh-token.expiration}")
        private long refreshExpiration;

        public String extractUsername(String token) {
            return extractClaims(token, Claims::getSubject);
        }

        public <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {
            final Claims claims = extractAllClaims(token);
            return claimsResolver.apply(claims);
        }

        public String generateToken(UserDetails userDetails) {
            return generateToken(new HashMap<>(), userDetails);
        }

        public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
            return buildToken(extraClaims, userDetails, jwtExpiration);
        }

        public String generateRefreshToken(UserDetails userDetails) {
            return buildToken(new HashMap<>(), userDetails, refreshExpiration);
        }

        private String buildToken(
                Map<String, Object> extraClaims,
                UserDetails userDetails,
                long expiration
        ) {
            String token = Jwts.builder()
                    .setSubject(userDetails.getUsername())
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + expiration))
                    .signWith(SignatureAlgorithm.HS256, getSignInKey())
                    .compact();

            // Log the token and the decoded key
            System.out.println("Generated Token: " + token);
            System.out.println("Decoded Key: " + new String(Decoders.BASE64.decode(secretKey)));

            return token;
        }
        public boolean isTokenValid(String token, UserDetails userDetails) {
            final String username = extractUsername(token);
            return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
        }

        private boolean isTokenExpired(String token) {
            return extractExpiration(token).before(new Date());
        }

        private Date extractExpiration(String token) {
            return extractClaims(token, Claims::getExpiration);
        }

        private Claims extractAllClaims(String token) {
            return Jwts
                    .parserBuilder()
                    .setSigningKey(getSignInKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        }

        private Key getSignInKey() {
            byte[] keyBytes = Decoders.BASE64.decode((secretKey).trim());
            return Keys.hmacShaKeyFor(keyBytes);

        }
    }