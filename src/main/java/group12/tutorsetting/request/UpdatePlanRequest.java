package group12.tutorsetting.request;

public class UpdatePlanRequest extends TutorSettingRequest {
    private int planNo;

    public UpdatePlanRequest() {

    }

    public UpdatePlanRequest(String token, int planNo) {
        super(token);
        this.planNo = planNo;
    }

    public void setToken(String token) {
        super.setToken(token);
    }

    public void setPlanNo(int planNo) {
        this.planNo = planNo;
    }

    public String getToken() {
        return super.getToken();
    }

    public int getPlanNo() {
        return planNo;
    }

    @Override
    public String toString() {
        return super.toString() + " planNo: " + planNo;
    }
}
