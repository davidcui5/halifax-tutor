package group12.tutor_setting.request;

public class UpdatePlanRequest {
    private String token;
    private int planNo;

    public void setToken(String token) {
        this.token = token;
    }

    public void setPlanNo(int planNo) {
        this.planNo = planNo;
    }

    public String getToken() {
        return token;
    }

    public int getPlanNo() {
        return planNo;
    }
}
