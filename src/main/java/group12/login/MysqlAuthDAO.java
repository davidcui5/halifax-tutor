package group12.login;

import group12.data_access.GetStudentSQLOperation;
import group12.data_access.SQLOperationTemplate;
import group12.data_access.Student;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MysqlAuthDAO implements IAuthDAO {

    private DataSource dataSource;
    private static final Logger logger = LogManager.getLogger(MysqlAuthDAO.class);

    public MysqlAuthDAO(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public void closeConnections(Connection con, PreparedStatement ps, ResultSet rs) throws  SQLException{
        if(rs != null){
            rs.close();
        }
        if(ps != null){
            ps.close();
        }
        if(con != null){
            con.close();
        }
    }

    @Override
    public UserDTO getStudentByEmail(String email) {
        SQLOperationTemplate op = new GetStudentSQLOperation(email);
        Student student = (Student) op.executeMysqlQuery();
        UserDTO user = new UserDTO();
        user.setPassword(student.getPassword());
        user.setIsActivated(student.isActivated());
        user.setIsBanned(student.isBanned());
        return user;
    }

    @Override
    public UserDTO getTutorByEmail(String email) {
        String sql = "SELECT * FROM Tutor Where Email =?";
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        UserDTO tutor = null;
        try {
            con = dataSource.getConnection();
            logger.info("Created DB Connection for getTutorByEmail " + email);
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if(rs.next()){
                tutor = new UserDTO();
                tutor.setPassword( rs.getString("Password") );
                tutor.setIsActivated( rs.getBoolean("AccountActivation") );
                tutor.setIsBanned( rs.getBoolean("Banned") );
            }
        } catch (SQLException e) {
            logger.error(email,e);
        } finally {
            try{
                closeConnections(con, ps, rs);
                logger.info("Closed DB Connection for getTutorByEmail " + email);
            }catch(Exception e){
                logger.error(email,e);
            }
        }
        return tutor;
    }

    @Override
    public UserDTO getAdminByEmail(String email) {
        String sql = "SELECT * FROM Admin Where Email =?";
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        UserDTO admin = null;
        try {
            con = dataSource.getConnection();
            logger.info("Created DB Connection for getAdminByEmail " + email);
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if(rs.next()){
                admin = new UserDTO();
                admin.setPassword( rs.getString("Password") );
            }
        } catch (SQLException e) {
            logger.error(email,e);
        } finally {
            try{
                closeConnections(con, ps, rs);
                logger.info("Closed DB Connection for getAdminByEmail " + email);
            }catch(Exception e){
                logger.error(email,e);
            }
        }
        return admin;
    }

}
