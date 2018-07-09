package group12.DBConnection;

import group12.DatabaseInterface;
import group12.Registration.Student;
import group12.Registration.Tutor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;


@Transactional
@Component
@ComponentScan
@ImportResource("classpath:spring.xml")
public class DBDAO implements DatabaseInterface {

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
        }
        return result;
    }

    @Override
    public boolean isCreditCardNew(String creditCardNum) {
        return false;
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
            throw new RuntimeException(ex);
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
            throw new RuntimeException(ex);
        }
        return result;
    }

    @Override
    public int getStudentId(String email) {
        String sql = "SELECT * FROM Student Where Email =?";
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int result = 0;
        try {
            con = dataSource.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            rs.first();
            result = rs.getInt("ID");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                ps.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public int getTutorID(String email) {
        String sql = "SELECT * FROM Tutor Where Email =?";
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int result = 0;
        try {
            con = dataSource.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            rs.first();
            result = rs.getInt("ID");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                ps.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public boolean saveActivationCode(String code) {
        String sql = "INSERT into ActivationTable (AcivationCode, Date) VALUES (?,NOW())";
        Connection con = null;
        PreparedStatement ps;
        try {
            con = dataSource.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, code);
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
    public boolean activateStudent(int id, String activateCode) {
        String sql = "SELECT * From ActivationTable where AcivationCode like ?";
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean result = false;
        try {
            con = dataSource.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, activateCode);
            rs = ps.executeQuery();
            rs.first();
            Date date = rs.getDate("Date");
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            LocalDate pdate = LocalDate.of(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
            LocalDate now = LocalDate.now();
            Period diff = Period.between(pdate, now);
            //Activate student
            System.out.println(cal.get(Calendar.YEAR) + " " + cal.get(Calendar.MONTH) + " " + cal.get(Calendar.DAY_OF_MONTH));
            System.out.println(diff.getDays() + " " + diff.getMonths() + " " + diff.getYears());
            if (diff.getDays() <= 1 && diff.getYears() == 0 && diff.getMonths() == 1) {
                sql = "update Student SET AccountActivation = 1 WHERE ID=?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, id);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                ps.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public boolean activateTutor(int id, String activateCode) {
        String sql = "SELECT * From ActivationTable where AcivationCode like ?";
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean result = false;
        try {
            con = dataSource.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, activateCode);
            rs = ps.executeQuery();
            rs.first();
            Date date = rs.getDate("Date");
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            LocalDate pdate = LocalDate.of(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
            LocalDate now = LocalDate.now();
            Period diff = Period.between(pdate, now);
            //Activate student
            System.out.println(cal.get(Calendar.YEAR) + " " + cal.get(Calendar.MONTH) + " " + cal.get(Calendar.DAY_OF_MONTH));
            System.out.println(diff.getDays() + " " + diff.getMonths() + " " + diff.getYears());
            if (diff.getDays() <= 1 && diff.getYears() == 0 && diff.getMonths() == 1) {
                sql = "update Tutor SET AccountActivation = 1 WHERE ID=?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, id);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                ps.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

}
