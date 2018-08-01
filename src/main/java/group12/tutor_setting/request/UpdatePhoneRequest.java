package group12.tutor_setting.request;

public class UpdatePhoneRequest {
    private String token;
    private String phone;

    public void setToken(String token) {
        this.token = token;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getToken() {
        return token;
    }

    public String getPhone() {
        return phone;
    }
}
