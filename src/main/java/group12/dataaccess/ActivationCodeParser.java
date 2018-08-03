package group12.dataaccess;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ActivationCodeParser {
    public ActivationCode parse(ResultSet rs) throws SQLException {
        ActivationCode activationCode = new ActivationCode();
        activationCode.setActivationCode(rs.getString("AcivationCode"));
        activationCode.setDate(rs.getDate("Date"));
        return activationCode;
    }
}
