package group12.tutor_setting.request;

public class UpdateEmailRequest {
    private String token;
    private String email;

    public void setToken(String token) {
        this.token = token;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public String getEmail() {
        return email;
    }
}