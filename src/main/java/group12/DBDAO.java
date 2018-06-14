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
//        RowMapper<Student> rowMapper = new StudentRowMapper();
//        Student student = jdbcTemplate.queryForObject(sql, rowMapper, email, password);
//        return student.getEmail().equals(email);
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
                System.out.println("Employee Found::" + st);
            } else {
                System.out.println("No Employee found with id=" + email);
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
        return false;
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
