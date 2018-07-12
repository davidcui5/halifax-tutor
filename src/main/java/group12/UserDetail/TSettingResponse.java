package group12.UserDetail;

import java.util.ArrayList;

public class TSettingResponse {

    private String result; //SUCCESS or FAILURE
    private String detail; //failure reason

    public TSettingResponse(){
        result = "";
        detail = "";

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


    @Override
    public String toString() {
        return "TSetting Result: " + result + " Detail: " + detail;
    }


}
