package group12.tutorsetting.request;

public class ResendConfirmationRequest {
    private String token;

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
