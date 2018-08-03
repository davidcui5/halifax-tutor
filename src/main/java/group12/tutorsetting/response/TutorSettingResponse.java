package group12.tutorsetting.response;

public class TutorSettingResponse {
    private boolean success;

    public TutorSettingResponse() {
        success = false;
    }

    public TutorSettingResponse(Boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
