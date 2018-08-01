package group12.tutorsetting.request;

public class UpdateExperienceRequest {
    private String token;
    private String experience;

    public void setToken(String token) {
        this.token = token;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getToken() {
        return token;
    }

    public String getExperience() {
        return experience;
    }
}
