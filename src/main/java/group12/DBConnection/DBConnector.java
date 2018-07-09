package group12.DBConnection;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DBConnector {
    public DBConnector(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private DataSource dataSource;
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public ResultSet getResult(String query, String... parameters) {

        try {
            con = dataSource.getConnection();
            ps = con.prepareStatement(query);
            for (int i = 0; i < parameters.length; i++) {
                ps.setString(i + 1, parameters[i]);
            }
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                assert (con != null);
                con.close();
                ps.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return rs;
    }
}
