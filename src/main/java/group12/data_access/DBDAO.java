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
        RegStudent regStudent = new RegStudent(student.getFirstName(), student.getLastName()
                , student.getEmail(), student.getPassword(), student.getSchool(), student.getPhoneNumber());
        int result = (int) regStudent.executeMysqlQuery();
        if (result == 1)
            return true;
        else
            return false;
    }

    @Override
    public boolean authorizeStudent(String email, String password) {
        AuthorizeStudent authorizeStudent = new AuthorizeStudent(email, password);
        Student student = (Student) authorizeStudent.executeMysqlQuery();
        if (student.getEmail().equals(email) && student.getPassword().equals(password))
            return true;
        else return false;
    }

    @Override
    public boolean authorizeTutor(String email, String password) {
        String sql = "SELECT AuthorizeTutor(?,?)";
        boolean result = false;
        try {
            rs = getResult(sql, email, password);
            rs.next();
            result = rs.getBoolean(1);
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
    public boolean regTutor(TutorSignupForm tutor) {
        String sql = "select RegTutor(?,?,?,?,?)";
        boolean result = false;
        try {
            rs = getResult(sql, tutor.getFirstName(), tutor.getLastName(), tutor.getEmail()
                    , tutor.getPassword(), tutor.getPhoneNumber());
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
    public int getStudentId(String email) {
        String sql = "SELECT GetStudentId(?)";
        int result = 0;
        try {
            rs = getResult(sql, email);
            rs.next();
            result = rs.getInt(1);
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
    public int getTutorID(String email) {
        String sql = "SELECT GetTutorId(?)";
        int result = 0;
        try {
            rs = getResult(sql, email);
            rs.next();
            result = rs.getInt(1);
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
    public boolean saveActivationCode(String code) {
        String sql = "Select SaveActivationCode(?)";
        boolean result = false;
        try {
            rs = getResult(sql, code);
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
    public boolean activateStudent(int id, String activateCode) {
        String sql = "SELECT ActivateStudent(?,?)";
        boolean result = false;
        try {
            rs = getResult(sql, String.valueOf(id), activateCode);
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
    public boolean activateTutor(int id, String activateCode) {
        String sql = "SELECT ActivateTutor(?,?)";
        boolean result = false;
        try {
            rs = getResult(sql, String.valueOf(id), activateCode);
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

    public boolean delelteStudent(int id) {
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

    public boolean delelteTutor(int id) {
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
