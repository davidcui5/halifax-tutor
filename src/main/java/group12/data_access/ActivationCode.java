package group12.data_access;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class ActivationCode {
    private String activationCode;
    private Date date;

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
