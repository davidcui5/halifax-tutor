package group12.tutorsetting.request;

public class CancelSubscriptionRequest {
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
