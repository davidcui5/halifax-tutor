package group12.search.response;

public class NoLoginSearchResponse {
    private boolean success;

    public NoLoginSearchResponse() {
        success = false;
    }

    public NoLoginSearchResponse(boolean success) {
        this.success = success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }
}
