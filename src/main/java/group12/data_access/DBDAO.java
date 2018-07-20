package group12.data_access;


import group12.data_access.DatabaseInterface;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import group12.registration.StudentSignupForm;
import group12.registration.TutorSignupForm;
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
        assert (con != null);
        con.close();
        assert (ps != null);
        ps.close();
        assert (rs != null);
        rs.close();
    }

    @Override
    public boolean isEmailNew(String email) {
        IsEmailNewSQLOperation isEmailNewSQLOperation = new IsEmailNewSQLOperation(email);
        int numberOfEmails = (int) isEmailNewSQLOperation.executeMysqlQuery();
        if (numberOfEmails > 0)
            return false;
        else return true;
    }

    @Override
    public boolean isPhoneNumberNew(String phoneNumber) {
        IsPhoneNewSQLOperation isPhoneNewSQLOperation = new IsPhoneNewSQLOperation(phoneNumber);
        int numberOfEmails = (int) isPhoneNewSQLOperation.executeMysqlQuery();
        if (numberOfEmails > 0)
            return false;
        else return true;
    }

    @Override
    public boolean isCreditCardNew(String creditCardNum) {
        IsCreditCardNewSQLOperation isCreditCardNewSQLOperation = new IsCreditCardNewSQLOperation(creditCardNum);
        int numberOfEmails = (int) isCreditCardNewSQLOperation.executeMysqlQuery();
        if (numberOfEmails > 0)
            return false;
        else return true;
    }

    @Override
    public boolean regStudent(StudentSignupForm student) {
        RegStudentSQLOperation regStudent = new RegStudentSQLOperation(student.getFirstName(), student.getLastName()
                , student.getEmail(), student.getPassword(), student.getSchool(), student.getPhoneNumber());
        int result = (int) regStudent.executeMysqlQuery();
        if (result == 1)
            return true;
        else
            return false;
    }

    @Override
    public boolean authorizeStudent(String email, String password) {
        AuthorizeStudentSQLOperation authorizeStudent = new AuthorizeStudentSQLOperation(email, password);
        Student student = (Student) authorizeStudent.executeMysqlQuery();
        if (student.getEmail().equals(email) && student.getPassword().equals(password))
            return true;
        else return false;
    }

    @Override
    public boolean authorizeTutor(String email, String password) {
        AuthorizeTutorSQLOperation authorizeTutorSQLOperation = new AuthorizeTutorSQLOperation(email, password);
        Tutor tutor = (Tutor) authorizeTutorSQLOperation.executeMysqlQuery();
        if (tutor.getEmail().equals(email) && tutor.getPassword().equals(password))
            return true;
        else return false;
    }


    @Override
    public boolean regTutor(TutorSignupForm tutor) {
        RegTutorSQLOperation regTutorSQLOperation = new RegTutorSQLOperation(tutor.getFirstName(), tutor.getLastName(), tutor.getEmail()
                , tutor.getPassword(), tutor.getPhoneNumber(), tutor.getCreditCardNumber()
                , tutor.getExpireDate(), tutor.getSecurityCode());
        int result = (int) regTutorSQLOperation.executeMysqlQuery();
        if (result == 1)
            return true;
        else return false;
    }

    @Override
    public int getStudentId(String email) {
        GetStudentIdSQLOperation getStudentIdSQLOperation = new GetStudentIdSQLOperation(email);
        Student student = (Student) getStudentIdSQLOperation.executeMysqlQuery();
        return student.getStudentID();
    }

    @Override
    public int getTutorID(String email) {
        GetTutorIdSQLOperation tutorIdSQLOperation = new GetTutorIdSQLOperation(email);
        Tutor tutor = (Tutor) tutorIdSQLOperation.executeMysqlQuery();
        return tutor.getTutorID();
    }

    @Override
    public boolean saveActivationCode(String code) {
        SaveActivationCodeSQLOperation saveActivationCodeSQLOperation = new SaveActivationCodeSQLOperation(code);
        int result = (int) saveActivationCodeSQLOperation.executeMysqlQuery();
        if (result == 1)
            return true;
        else
            return false;
    }

    @Override
    public boolean activateStudent(int id, String activateCode) {
        ActivateStudentSQLOperation activateStudentSQLOperation = new ActivateStudentSQLOperation(id);
        int result = (int) activateStudentSQLOperation.executeMysqlQuery();
        if (result == 1)
            return true;
        else return false;
    }

    @Override
    public boolean activateTutor(int id, String activateCode) {
        ActivateTutorSQLOperation activateTutorSQLOperation = new ActivateTutorSQLOperation(id);
        int result = (int) activateTutorSQLOperation.executeMysqlQuery();
        if (result == 1)
            return true;
        else
            return false;
    }

    @Override
    public boolean deleteStudent(int id) {
        String sql = "SELECT DeleteStudent(?)";
        boolean result = false;
        try {
            rs = getResult(sql, String.valueOf(id));
            rs.next();
            if (rs.getInt(1) == 1)
                result = true;
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        } finally {
            try {
                closeConnections();
            } catch (SQLException e) {
                e.printStackTrace();
                logger.error(e.getMessage());
            }
        }
        return result;
    }

    @Override
    public boolean deleteTutor(int id) {
        String sql = "SELECT DeleteTutor(?)";
        boolean result = false;
        try {
            rs = getResult(sql, String.valueOf(id));
            rs.next();
            if (rs.getInt(1) == 1)
                result = true;
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        } finally {
            try {
                closeConnections();
            } catch (SQLException e) {
                e.printStackTrace();
                logger.error(e.getMessage());
            }
        }
        return result;
    }

    @Override
    public boolean updateStudentPassword(String email, String new_password) {
        String sql = "UPDATE Student SET Password=? WHERE Email=?";
        Connection con = null;
        PreparedStatement ps;
        try {
            con = dataSource.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, new_password);
            ps.setString(2, email);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                    return true;
                } catch (SQLException ex) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean updateTutorPassword
            (String email, String new_password) {
        String sql = "UPDATE Tutor SET Password=? WHERE Email=?";
        Connection con = null;
        PreparedStatement ps;
        try {
            con = dataSource.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, new_password);
            ps.setString(2, email);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                    return true;
                } catch (SQLException ex) {
                    return false;
                }
            }
        }
        return true;
    }


}
