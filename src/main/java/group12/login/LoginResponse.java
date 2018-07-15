package group12.login;

public class LoginResponse {

    private AuthenticationResult result; //SUCCESS or FAILURE
    private String message; //message
    private String url; //url to redirect to
    private String token; //access token

    public LoginResponse(AuthenticationResult result, String message){
        this.result = result;
        this.message = message;
        url = "";
        token = "";
    }

    public LoginResponse(AuthenticationResult result, String message, String url, String token){
        this.result = result;
        this.message = message;
        this.url = url;
        this.token = token;
    }

    public void setResult(AuthenticationResult result) {
        this.result = result;
    }

    public AuthenticationResult getResult() {
        return result;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    @Override
    public String toString() {
        return "Login Result: " + result + " Message: " + message + " Url: " + url + " Token: " + token;
    }
}
