package group12.tutorsetting.request;

public class UpdatePhotoRequest extends TutorSettingRequest {
    private String photoURL;

    public UpdatePhotoRequest() {

    }

    public UpdatePhotoRequest(String token, String photoURL) {
        super(token);
        this.photoURL = photoURL;
    }

    public void setToken(String token) {
        super.setToken(token);
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public String getToken() {
        return super.getToken();
    }

    public String getPhotoURL() {
        return photoURL;
    }

    @Override
    public String toString() {
        return super.toString() + " photoURL: " + photoURL;
    }
}
