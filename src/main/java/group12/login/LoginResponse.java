package group12.login;

public class LoginResponse {

    private String result; //SUCCESS or FAILURE
    private String detail; //failure reason
    private String url; //url to redirect to
    private String token; //access token

    public LoginResponse(){
        result = "";
        detail = "";
        url = "";
        token = "";
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getDetail() {
        return detail;
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
        return "Login Result: " + result + "Detail: " + detail + "Url: " + url + "Token: " + token;
    }
}
