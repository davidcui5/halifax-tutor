package group12.search;

public class IdentityRequest {
    private String token;

    void setToken(String token) {
        this.token = token;
    }

    String getToken() {
        return token;
    }

    @Override
    public String toString() {
        return "token: " + token;
    }
}