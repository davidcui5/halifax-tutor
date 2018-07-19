package group12.token_auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

import java.security.Key;


public class JWTAccessToken implements IAccessToken {
    private static final JWTAccessToken INSTANCE = new JWTAccessToken();

    private Key key;
    private SignatureAlgorithm algorithm;

    private JWTAccessToken() {
        this.key = MacProvider.generateKey();
        this.algorithm = SignatureAlgorithm.HS512;
    }

    private JWTAccessToken(Key key, SignatureAlgorithm algorithm) {
        this.key = key;
        this.algorithm = algorithm;
    }

    public static JWTAccessToken getInstance() {
        return INSTANCE;
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
