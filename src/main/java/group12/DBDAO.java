package group12;

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

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public boolean isEmailNew(String email) {
        String sql = "SELECT * FROM Student Where Email =?";
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean result = true;
        try {
            con = dataSource.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            result = rs.first();
            if (!result) {
                sql = "SELECT * FROM Tutor Where Email =?";
                ps = con.prepareStatement(sql);
                ps.setString(1, email);
                rs = ps.executeQuery();
                result = rs.first();
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
    public boolean isPhoneNumberNew(String number) {
        String sql = "SELECT * FROM Student Where PhoneNumber =?";
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean result = false;
        try {
            con = dataSource.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, number);
            rs = ps.executeQuery();
            result = rs.first();
            if (!result) {
                sql = "SELECT * FROM Tutor Where PhoneNumber =?";
                ps = con.prepareStatement(sql);
                ps.setString(1, number);
                rs = ps.executeQuery();
                result = rs.first();
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
    public boolean isCreditCardNew(String creditCardNum) {
        return false;
    }

    @Override
    public boolean authorizeStudent(String email, String password) {
        String sql = "SELECT * FROM Student Where Email =? And Password = ?";
        StudentSignupForm st = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = dataSource.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                st = new StudentSignupForm();
                st.setFirstName(rs.getString("FirstName"));
                st.setLastName(rs.getString("LastName"));
                st.setEmail(rs.getString("email"));
                System.out.println("Student Found::" + st);
            } else {
                System.out.println("No Student found with id=" + email);
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
        return st.getEmail().equals(email);
    }

    @Override
    public boolean authorizeTutor(String email, String password) {
        String sql = "SELECT * FROM Tutor Where Email =? And Password = ?";
        TutorSignupForm tutor = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = dataSource.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                tutor = new TutorSignupForm();
                tutor.setFirstName(rs.getString("FirstName"));
                tutor.setLastName(rs.getString("LastName"));
                tutor.setEmail(rs.getString("email"));
                System.out.println("Tutor Found::" + tutor);
            } else {
                System.out.println("No Tutor found with id=" + email);
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
        return tutor.getEmail().equals(email);
    }

    @Override
    public boolean regStudent(StudentSignupForm student) {
        String sql = "INSERT INTO Student (FirstName, LastName, Email, Password, AccountActivation, School,PhoneNumber) VALUES (?,?,?,?,?,?,?)";
        Connection con = null;
        PreparedStatement ps;
        try {
            con = dataSource.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, student.getFirstName());
            ps.setString(2, student.getLastName());
            ps.setString(3, student.getEmail());
            ps.setString(4, student.getPassword());
            ps.setInt(5, 0);
            ps.setString(6, student.getSchool());
            ps.setString(7, student.getPhoneNumber());
            //TODO send activation email
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
    public boolean regTutor(TutorSignupForm tutor) {
        String sql = "INSERT INTO Tutor (FirstName, LastName, Email, Password, AccountActivation) VALUES (?, ?, ?, ?, ?)";
        Connection con = null;
        PreparedStatement ps;
        try {
            con = dataSource.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, tutor.getFirstName());
            ps.setString(2, tutor.getLastName());
            ps.setString(3, tutor.getEmail());
            ps.setString(4, tutor.getPassword());
            ps.setInt(5, 0);
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
