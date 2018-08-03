package group12.tutorsetting.request;

public class UpdateEducationRequest {
    private String token;
    private String education;

    public void setToken(String token) {
        this.token = token;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getToken() {
        return token;
    }

    public String getEducation() {
        return education;
    }

    @Override
    public String toString() {
        return "token: " + token + " education: " + education;
    }
}
