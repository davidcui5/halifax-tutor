package group12.tutorsetting.request;

public class TutorSettingRequest {
    private String token;

    public TutorSettingRequest() {
        token = "";
    }

    public TutorSettingRequest(String token) {
        this.token = token;
    }

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
