package group12.tokenauth;

public interface IAccessToken {
    public String generateToken(String userEmail);

    public String decodeToken(String token);
}
