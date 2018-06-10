package group12;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public class StudentsDAO implements IStudentsDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public boolean authorizeStudent(String email, String password) {
        String sql = "SELECT * FROM Student Where Email =? And Password = ?";
        RowMapper<Student> rowMapper = new StudentRowMapper();
        Student student = jdbcTemplate.queryForObject(sql, rowMapper, email, password);
        return student.getEmail().equals(email);
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
