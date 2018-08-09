package group12.search.request;

public class IdentityRequest {
    private String token;

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    @Override
    public String toString() {
        return "token: " + token;
    }
}