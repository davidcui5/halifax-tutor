package group12.token_auth;

public interface IAccessToken {
    public String generateToken(String userEmail);

    public String decodeToken(String token);
}
