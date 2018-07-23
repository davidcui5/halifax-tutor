package group12.data_access;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SaveTutorSQLOperation extends SQLOperationTemplate {
    public SaveTutorSQLOperation(Tutor tutor) {
        super(tutor);
    }

    @Override
    String makeSQL() {
        return "SELECT SaveTutor(?,?,?,?,?,?,?,?,?)";
    }

    @Override
    PreparedStatement addParameters(PreparedStatement ps) throws SQLException {
        Tutor t = (Tutor) getParameters().get(0);
        ps.setString(1, t.getFirstName());
        ps.setString(2, t.getLastName());
        ps.setString(3, t.getEmail());
        ps.setString(4, t.getPassword());
        ps.setString(5, t.getPhoneNumber());
        ps.setString(6, t.getCreditCardHolder());
        ps.setString(7, t.getCreditCardNum());
        ps.setString(8, t.getCreditCardExpiryDate());
        ps.setString(9, t.getSecurityCode());
        return ps;
    }

    @Override
    Object extractResultSet(ResultSet rs) throws SQLException {
        return rs.getBoolean(1);
    }

    @Override
    ResultSet execute(PreparedStatement ps) throws SQLException {
        ps.execute();
        return ps.getResultSet();
    }
}
