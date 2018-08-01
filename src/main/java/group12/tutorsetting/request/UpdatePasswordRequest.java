package group12.tutorsetting.request;

public class UpdatePasswordRequest {
    private String token;
    private String password;

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
}
