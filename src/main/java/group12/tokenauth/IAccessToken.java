package group12.tokenauth;

public interface IAccessToken {
    String generateToken(String userEmail);

    String decodeToken(String token);
}
