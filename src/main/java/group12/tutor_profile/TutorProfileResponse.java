package group12.tutor_profile;

import java.util.ArrayList;

public class TutorProfileResponse {
    private String result;
    private ArrayList<String> details;

    public TutorProfileResponse(){
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
