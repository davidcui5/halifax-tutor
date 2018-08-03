package group12.tokenauth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class JWTAccessToken implements IAccessToken {
    private static final Logger logger = LogManager.getLogger(JWTAccessToken.class);
    private static final JWTAccessToken INSTANCE = new JWTAccessToken();
    private final String secret = "secret";

    private SignatureAlgorithm algorithm;

    private JWTAccessToken() {
        this.algorithm = SignatureAlgorithm.HS512;
    }

    private JWTAccessToken(SignatureAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    public static JWTAccessToken getInstance() {
        return INSTANCE;
    }

    @Override
    public String generateToken(String userEmail) {
        return Jwts.builder().setSubject(userEmail).signWith(algorithm, secret).compact();
    }

    @Override
    public String decodeToken(String token) {
        try {
            return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }
}
