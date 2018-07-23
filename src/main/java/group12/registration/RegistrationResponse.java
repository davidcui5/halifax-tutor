package group12.registration;

public class RegistrationResponse {

    private String result;
    private String detail;

    public RegistrationResponse(String result, String detail){
        this.result = result;
        this.detail = detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getDetail() {
        return detail;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

}
