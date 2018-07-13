package group12.registration;

import java.util.ArrayList;

public class RegistrationResponse {

    private String result;
    private ArrayList<String> details;

    public RegistrationResponse(){
        result = "";
        details = new ArrayList<String>();
    }

    public void addDetail(String detail) {
        details.add(detail);
    }

    public ArrayList<String> getDetails() {
        return details;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

}
