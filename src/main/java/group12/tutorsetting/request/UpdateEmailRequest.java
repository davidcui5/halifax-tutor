package group12.tutorsetting.request;

public class UpdateEmailRequest extends TutorSettingRequest {
    private String email;

    public UpdateEmailRequest() {

    }

    public UpdateEmailRequest(String token, String email) {
        super(token);
        this.email = email;
    }

    public void setToken(String token) {
        super.setToken(token);
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return super.getToken();
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return super.toString() + " email: " + email;
    }
}
