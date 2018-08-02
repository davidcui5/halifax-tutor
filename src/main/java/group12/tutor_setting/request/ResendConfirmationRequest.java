package group12.tutor_setting.request;

public class ResendConfirmationRequest {
    private String token;

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
