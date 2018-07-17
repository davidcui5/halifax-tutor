package group12.UserSetting;

public class ChangePhoneForm {
    private String phone;


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        if( phone != null){
            return " Phone: " + phone;
        }
        else{
            return "Invalid Changing phone Form: Contains NULL";
        }
    }
}
