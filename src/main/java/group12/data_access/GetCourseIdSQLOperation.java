package group12.data_access;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetCourseIdSQLOperation extends SQLOperationTemplate {
    private static Logger logger = LogManager.getLogger(GetCourseIdSQLOperation.class);

    public GetCourseIdSQLOperation(String name, String school) {
        super(name, school);
    }

    @Override
    String makeSQL() {
        return "SELECT ID " +
                "FROM Course " +
                "WHERE Name = ? AND School = ?";
    }

    @Override
    PreparedStatement addParameters(PreparedStatement ps) throws SQLException {
        String name = (String) getParameters().get(0);
        String school = (String) getParameters().get(1);
        ps.setString(1, name);
        ps.setString(2, school);
        return ps;
    }

    @Override
    Object extractResultSet(ResultSet rs) throws SQLException {
        System.out.println(rs.getInt(1));
        return rs.getInt(1);
    }

    @Override
    ResultSet execute(PreparedStatement ps) throws SQLException {
        return ps.executeQuery();
    }
}
