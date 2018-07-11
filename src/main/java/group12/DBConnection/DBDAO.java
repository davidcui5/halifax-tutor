package group12.DBConnection;

import group12.App;
import group12.DatabaseInterface;
import group12.Registration.Student;
import group12.Registration.Tutor;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

@Transactional
@Component
@ComponentScan
@ImportResource("classpath:spring.xml")
public class DBDAO implements DatabaseInterface {

    private DataSource dataSource;
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Logger logger;

    public DBDAO() {
        logger = LogManager.getLogger("Logger DB");
    }

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
            logger.error("Query:" + query + " Input:" + Arrays.toString(parameters) + " Error:" + e.getMessage());
        } finally {

        }
        return null;
    }

    public void closeConnections() throws SQLException {
        con.close();
        ps.close();
        rs.close();
    }

    @Override
    public boolean isEmailNew(String email) {
        String sql = "SELECT IsEmailNew(?)";
        boolean result = false;
        try {
            rs = getResult(sql, email);
            rs.next();
            result = rs.getBoolean(1);
            closeConnections();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return result;
    }

    @Override
    public boolean isPhoneNumberNew(String phoneNumber) {
        String sql = "SELECT IsPhoneNew(?)";
        boolean result = false;
        try {
            rs = getResult(sql, phoneNumber);
            rs.next();
            result = rs.getBoolean(1);
            closeConnections();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return result;
    }

    @Override
    public boolean isCreditCardNew(String creditCardNum) {
        String sql = "SELECT IsCreditCardNew(?)";
        boolean result = false;
        try {
            rs = getResult(sql, creditCardNum);
            rs.next();
            result = rs.getBoolean(1);
            closeConnections();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return result;
    }

    @Override
    public boolean authorizeStudent(String email, String password) {
        String sql = "SELECT AuthorizeStudent(?,?)";
        boolean result = false;
        try {
            rs = getResult(sql, email, password);
            rs.next();
            result = rs.getBoolean(1);
            closeConnections();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return result;
    }

    @Override
    public boolean authorizeTutor(String email, String password) {
        String sql = "SELECT AuthorizeTutor(?,?)";
        boolean result = false;
        try {
            rs = getResult(sql, email, password);
            rs.next();
            result = rs.getBoolean(1);
            closeConnections();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return result;
    }

    @Override
    public boolean regStudent(Student student) {
        String sql = "select RegStudent(?,?,?,?,?,?)";
        boolean result = false;
        try {
            rs = getResult(sql, student.getFirstName(), student.getLastName(), student.getEmail()
                    , student.getPassword(), student.getSchool(), student.getPhoneNumber());
            rs.next();
            if (rs.getInt(1) == 1)
                result = true;
            closeConnections();
        } catch (SQLException ex) {
            ex.printStackTrace();
            logger.error(ex.getMessage());
        }
        return result;
    }

    @Override
    public boolean regTutor(Tutor tutor) {
        String sql = "select RegTutor(?,?,?,?,?)";
        boolean result = false;
        try {
            rs = getResult(sql, tutor.getFirstName(), tutor.getLastName(), tutor.getEmail()
                    , tutor.getPassword(), tutor.getPhoneNumber());
            rs.next();
            if (rs.getInt(1) == 1)
                result = true;
            closeConnections();
        } catch (SQLException ex) {
            ex.printStackTrace();
            logger.error(ex.getMessage());
        }
        return result;
    }

    @Override
    public int getStudentId(String email) {
        String sql = "SELECT GetStudentId(?)";
        int result = 0;
        try {
            rs = getResult(sql, email);
            rs.next();
            result = rs.getInt(1);
            closeConnections();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return result;
    }

    @Override
    public int getTutorID(String email) {
        String sql = "SELECT GetTutorId(?)";
        int result = 0;
        try {
            rs = getResult(sql, email);
            rs.next();
            result = rs.getInt(1);
            closeConnections();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean saveActivationCode(String code) {
        String sql = "Select SaveActivationCode(?)";
        boolean result = false;
        try {
            rs = getResult(sql, code);
            rs.next();
            if (rs.getInt(1) == 1)
                result = true;
            closeConnections();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return result;
    }

    @Override
    public boolean activateStudent(int id, String activateCode) {
        String sql = "SELECT ActivateStudent(?,?)";
        boolean result = false;
        try {
            rs = getResult(sql, String.valueOf(id), activateCode);
            rs.next();
            if (rs.getInt(1) == 1)
                result = true;
            closeConnections();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return result;
    }

    @Override
    public boolean activateTutor(int id, String activateCode) {
        String sql = "SELECT ActivateTutor(?,?)";
        boolean result = false;
        try {
            rs = getResult(sql, String.valueOf(id), activateCode);
            rs.next();
            if (rs.getInt(1) == 1)
                result = true;
            closeConnections();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return result;
    }

    public boolean delelteStudent(int id) {
        String sql = "SELECT DeleteStudent(?)";
        boolean result = false;
        try {
            rs = getResult(sql, String.valueOf(id));
            rs.next();
            if (rs.getInt(1) == 1)
                result = true;
            closeConnections();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return result;
    }

    public boolean delelteTutor(int id) {
        String sql = "SELECT DeleteTutor(?)";
        boolean result = false;
        try {
            rs = getResult(sql, String.valueOf(id));
            rs.next();
            if (rs.getInt(1) == 1)
                result = true;
            closeConnections();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return result;
    }
}
