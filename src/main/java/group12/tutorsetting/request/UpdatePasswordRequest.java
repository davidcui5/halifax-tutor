package group12.tutorsetting.request;

public class UpdatePasswordRequest extends TutorSettingRequest {
    private String password;

    public UpdatePasswordRequest() {
    }

    public UpdatePasswordRequest(String token, String password) {
        super(token);
        this.password = password;
    }

    public void setToken(String token) {
        super.setToken(token);
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return super.getToken();
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return super.toString() + " password: " + password;
    }
}
