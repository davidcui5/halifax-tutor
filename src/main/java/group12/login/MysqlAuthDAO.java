package group12.login;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MysqlAuthDAO implements IAuthDAO {

    private DataSource dataSource;
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public ResultSet getResult(String query, String... parameters) {
        con = null;
        ps = null;
        rs = null;
        try {
            con = dataSource.getConnection();
            ps = con.prepareStatement(query);
            for (int i = 0; i < parameters.length; i++) {
                ps.setString(i + 1, parameters[i]);
            }
            return ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void closeConnections() throws SQLException {
        con.close();
        ps.close();
        rs.close();
    }

    @Override
    public UserDTO getStudentByEmail(String email) {
        String sql = "SELECT * FROM Student Where Email =?";
        try {
            rs = getResult(sql, email);
            if(rs.next())
            {
                UserDTO student = new UserDTO();
                student.setPassword( rs.getString("Password") );
                student.setIsActivated( rs.getBoolean("AccountActivation") );
                student.setIsBanned( rs.getBoolean("Banned") );
                return student;
            }
            closeConnections();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public UserDTO getTutorByEmail(String email) {
        String sql = "SELECT * FROM Tutor Where Email =?";
        try {
            rs = getResult(sql, email);
            if(rs.next())
            {
                UserDTO tutor = new UserDTO();
                tutor.setPassword( rs.getString("Password") );
                tutor.setIsActivated( rs.getBoolean("AccountActivation") );
                tutor.setIsBanned( rs.getBoolean("Banned") );
                return tutor;
            }
            closeConnections();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public UserDTO getAdminByEmail(String email) {
        String sql = "SELECT * FROM Admin Where Email =?";
        try {
            rs = getResult(sql, email);
            if(rs.next())
            {
                UserDTO admin = new UserDTO();
                admin.setPassword( rs.getString("Password") );
                return admin;
            }
            closeConnections();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
