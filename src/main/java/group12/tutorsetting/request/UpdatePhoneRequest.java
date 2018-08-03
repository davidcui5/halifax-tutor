package group12.tutorsetting.request;

public class UpdatePhoneRequest extends TutorSettingRequest {
    private String phone;

    public void setToken(String token) {
        super.setToken(token);
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getToken() {
        return super.getToken();
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return super.toString() + " phone: " + phone;
    }
}
