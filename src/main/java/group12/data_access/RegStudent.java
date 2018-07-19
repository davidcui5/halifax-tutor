package group12.data_access;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegStudent extends SQLOperationTemplate {
    public RegStudent(Object... parameters) {
        super(parameters);
    }

    @Override
    String makeSQL() {
        return "SELECT RegStudent(?,?,?,?,?,?)";
    }

    @Override
    PreparedStatement addParameters(PreparedStatement ps) throws SQLException {
        String firstName = (String) getParameters().get(0);
        String lastName = (String) getParameters().get(1);
        String email = (String) getParameters().get(2);
        String password = (String) getParameters().get(3);
        String school = (String) getParameters().get(4);
        String phoneNumber = (String) getParameters().get(5);
        ps.setString(1, firstName);
        ps.setString(2, lastName);
        ps.setString(3, email);
        ps.setString(4, password);
        ps.setString(5, school);
        ps.setString(6, phoneNumber);
        return ps;
    }

    @Override
    Object extractResultSet(ResultSet rs) throws SQLException {
        int result;
        rs.next();
        result = rs.getInt(1);
        return result;
    }

    @Override
    ResultSet execute(PreparedStatement ps) throws SQLException {
        return ps.executeQuery();
    }
}
