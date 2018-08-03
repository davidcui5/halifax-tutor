package group12.tutorsetting.request;

public class GetCoursesRequest {
    private String token;

    public GetCoursesRequest(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
