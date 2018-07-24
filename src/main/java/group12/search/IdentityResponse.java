package group12.search;

public class IdentityResponse {
    private boolean success;
    private String type;

    public IdentityResponse() {
        success = false;
        type = "student";
    }

    public IdentityResponse(boolean success, String type) {
        this.success = success;
        this.type = type;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}