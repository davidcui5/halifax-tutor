package group12.token_auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

import java.security.Key;


public class JWTAccessToken implements IAccessToken {
    private Key key;
    private SignatureAlgorithm algorithm;

    public JWTAccessToken() {
        this.key = MacProvider.generateKey();
        this.algorithm = SignatureAlgorithm.HS512;
    }

    public JWTAccessToken(Key key, SignatureAlgorithm algorithm) {
        this.key = key;
        this.algorithm = algorithm;
    }

    @Override
    public String generateToken(String userEmail) {
        return Jwts.builder().setSubject(userEmail).signWith(this.algorithm, this.key).compact();
    }

    @Override
    public String decodeToken(String token) {
        try {
            String userEmail = Jwts.parser().setSigningKey(this.key).parseClaimsJws(token).getBody().getSubject();
            return userEmail;
        } catch (Exception e) {
            return null;
        }
    }
}
