package group12.tutorsetting.request;

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

    @Override
    public String toString() {
        return "token: " + token + " photoURL: " + photoURL;
    }
}
