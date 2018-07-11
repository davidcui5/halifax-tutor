package group12.login;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MysqlAuthDAO implements IAuthDAO {

    private DataSource dataSource;
    private Logger logger;
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public MysqlAuthDAO(DataSource dataSource){
        this.dataSource = dataSource;
        logger = LogManager.getLogger(MysqlAuthDAO.class);
    }

    public ResultSet getResult(String query, String... parameters) {
        con = null;
        ps = null;
        rs = null;
        try {
            con = dataSource.getConnection();
            logger.log(Level.INFO, "Created DB Connection");
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
        logger.log(Level.INFO, "Closed DB Connection");
    }

    @Override
    public UserDTO getStudentByEmail(String email) {
        String sql = "SELECT * FROM Student Where Email =?";
        try {
            rs = getResult(sql, email);
            if(rs.next()){
                UserDTO student = new UserDTO();
                student.setPassword( rs.getString("Password") );
                student.setIsActivated( rs.getBoolean("AccountActivation") );
                student.setIsBanned( rs.getBoolean("Banned") );
                closeConnections();
                return student;
            }
            else {
                closeConnections();
                return null;
            }
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
            if(rs.next()){
                UserDTO tutor = new UserDTO();
                tutor.setPassword( rs.getString("Password") );
                tutor.setIsActivated( rs.getBoolean("AccountActivation") );
                tutor.setIsBanned( rs.getBoolean("Banned") );
                closeConnections();
                return tutor;
            }
            else{
                closeConnections();
                return null;
            }
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
            if(rs.next()){
                UserDTO admin = new UserDTO();
                admin.setPassword( rs.getString("Password") );
                closeConnections();
                return admin;
            }
            else{
                closeConnections();
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
