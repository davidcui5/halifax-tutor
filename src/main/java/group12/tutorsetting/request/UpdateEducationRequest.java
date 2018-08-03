package group12.tutorsetting.request;

public class UpdateEducationRequest extends TutorSettingRequest {
    private String education;

    public UpdateEducationRequest() {

    }

    public UpdateEducationRequest(String token, String education) {
        super(token);
        this.education = education;
    }

    public void setToken(String token) {
        super.setToken(token);
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getToken() {
        return super.getToken();
    }

    public String getEducation() {
        return education;
    }

    @Override
    public String toString() {
        return super.toString() + " education: " + education;
    }
}
