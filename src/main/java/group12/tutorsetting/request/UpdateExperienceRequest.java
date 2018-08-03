package group12.tutorsetting.request;

public class UpdateExperienceRequest extends TutorSettingRequest {
    private String experience;

    public void setToken(String token) {
        super.setToken(token);
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getToken() {
        return super.getToken();
    }

    public String getExperience() {
        return experience;
    }

    @Override
    public String toString() {
        return super.toString() + " experience: " + experience;
    }
}
