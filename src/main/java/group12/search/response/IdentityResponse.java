package group12.search.response;

public class IdentityResponse {
    private boolean success;
    private Type type;

    public IdentityResponse() {
        success = false;
        type = Type.STUDENT;
    }

    public IdentityResponse(boolean success, Type type) {
        this.success = success;
        this.type = type;
    }

    public boolean isSuccess() {
        return success;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}