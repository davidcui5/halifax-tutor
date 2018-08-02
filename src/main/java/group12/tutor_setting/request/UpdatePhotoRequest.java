package group12.tutor_setting.request;

public class UpdatePhotoRequest {
    private String token;
    private String photoURL;

    public void setToken(String token) {
        this.token = token;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public String getToken() {
        return token;
    }

    public String getPhotoURL() {
        return photoURL;
    }
}
