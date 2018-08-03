package group12.tutorsetting.request;

public class UpdatePasswordRequest {
    private String token;
    private String password;

    public UpdatePasswordRequest() {
        this.token = "";
        this.password = "";
    }

    public UpdatePasswordRequest(String token, String password) {
        this.token = token;
        this.password = password;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "token: " + token + " password: " + password;
    }
}
