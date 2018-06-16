package group12;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@Transactional
@Component
public class DBDAO implements IDBDao {
//    @Autowired
//    private JdbcTemplate jdbcTemplate;

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public boolean authorizeStudent(String email, String password) {
        String sql = "SELECT * FROM Student Where Email =? And Password = ?";
        Student st = null;
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
                st = new Student();
                st.setFirstName(rs.getString("FirstName"));
                st.setLastName(rs.getString("LastName"));
                st.setEmail(rs.getString("Email"));
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
        Tutor tutor = null;
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
                tutor = new Tutor();
                tutor.setFirstName(rs.getString("FirstName"));
                tutor.setLastName(rs.getString("LastName"));
                tutor.setEmail(rs.getString("Email"));
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
    public boolean regStudent(Student student) {
        return false;
    }

    @Override
    public boolean regTutor(Tutor tutor) {
        return false;
    }
}
