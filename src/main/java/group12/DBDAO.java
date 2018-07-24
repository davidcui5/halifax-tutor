package group12;


import group12.tutor_profile.TutorProfileForm;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
        String sql = "SELECT IsEmailNew(?)";
        boolean result = false;
        try {
            rs = getResult(sql, email);
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
    public boolean isPhoneNumberNew(String phoneNumber) {
        String sql = "SELECT IsPhoneNew(?)";
        boolean result = false;
        try {
            rs = getResult(sql, phoneNumber);
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
    public boolean isCreditCardNew(String creditCardNum) {
        String sql = "SELECT IsCreditCardNew(?)";
        boolean result = false;
        try {
            rs = getResult(sql, creditCardNum);
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
    public boolean authorizeStudent(String email, String password) {
        String sql = "SELECT AuthorizeStudent(?,?)";
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
    public boolean regStudent(StudentSignupForm student) {
        String sql = "select RegStudent(?,?,?,?,?,?)";
        boolean result = false;
        try {
            rs = getResult(sql, student.getFirstName(), student.getLastName(), student.getEmail()
                    , student.getPassword(), student.getSchool(), student.getPhoneNumber());
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
    public boolean updateTutorPassword(String email, String new_password) {
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

    @Override
    public TutorProfileForm getTutorProfile(int tutorId) {
        TutorProfileForm tutorProfileForm = new TutorProfileForm();

        String[] tutorInfo = getTutorInfo(tutorId);

        tutorProfileForm.setFirstName(tutorInfo[0]);
        tutorProfileForm.setLastName(tutorInfo[1]);
        tutorProfileForm.setPhoneNumber(tutorInfo[2]);
        tutorProfileForm.setEmail(tutorInfo[3]);
        tutorProfileForm.setEducation(tutorInfo[4]);
        tutorProfileForm.setExperience(tutorInfo[5]);
        tutorProfileForm.setRating(tutorInfo[6]);
        tutorProfileForm.setPhotoURL(tutorInfo[7]);

        ArrayList<String[]> courseList = getTutorCourses(tutorId);
        tutorProfileForm.setCourseList(courseList);

        int[] tutorSchedule = getTutorSchedule(tutorId);
        tutorProfileForm.setTutorSchedule(tutorSchedule);

        return tutorProfileForm;
    }

    @Override
    public String[] getTutorInfo(int tutorId) {
        String sqlTutor = "SELECT * FROM Tutor Where ID =?";
        Connection con = null;
        PreparedStatement psTutor = null;
        ResultSet rsTutor = null;
        String[] tutorInfo = new String[8];

        try {
            con = dataSource.getConnection();
            psTutor = con.prepareStatement(sqlTutor);
            psTutor.setString(1, String.valueOf(tutorId));
            rsTutor = psTutor.executeQuery();
            if (rsTutor.next()) {
                tutorInfo[0] = rsTutor.getString("FirstName");
                tutorInfo[1] = rsTutor.getString("LastName");
                tutorInfo[2] = rsTutor.getString("PhoneNumber");
                tutorInfo[3] = rsTutor.getString("Email");
                tutorInfo[4] = rsTutor.getString("Education");
                tutorInfo[5] = rsTutor.getString("Experience");
                tutorInfo[6] = rsTutor.getString("Rating");
                tutorInfo[7] = rsTutor.getString("PhotoUrl");
                System.out.println("Tutor Found:" + tutorId);
            } else {
                System.out.println("No Tutor found with id=" + tutorId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rsTutor.close();
                psTutor.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return tutorInfo;

    }

    @Override
    public ArrayList<String[]> getTutorCourses(int tutorId) {

        String sqlTutorCourse = "SELECT * FROM TutorCourse Where TutorId =?";
        PreparedStatement psTutorCourse = null;
        ResultSet rsTutorCourse = null;
        ArrayList<String[]> courseList = new ArrayList();
        String courseId;

        try {
            con = dataSource.getConnection();

            psTutorCourse = con.prepareStatement(sqlTutorCourse);
            psTutorCourse.setString(1, String.valueOf(tutorId));
            rsTutorCourse = psTutorCourse.executeQuery();

            while (rsTutorCourse.next()) {
                courseId = rsTutorCourse.getString("CourseId");
                String[] row = {getCourseFromCourseId(courseId)[0],getCourseFromCourseId(courseId)[1], rsTutorCourse.getString("Price")};
                courseList.add(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rsTutorCourse.close();
                psTutorCourse.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return courseList;
    }

    @Override
    public String[] getCourseFromCourseId(String courseId) {
        String sqlCourse = "SELECT * FROM Course Where ID =?";
        PreparedStatement psCourse = null;
        ResultSet rsCourse = null;
        String courseInfo[] = new String[2];

        try {
            con = dataSource.getConnection();
            psCourse = con.prepareStatement(sqlCourse);
            psCourse.setString(1, courseId);
            rsCourse = psCourse.executeQuery();
            if (rsCourse.next()) {
                courseInfo[0] = rsCourse.getString("Name");
                courseInfo[1] = rsCourse.getString("School");

            } else {
                System.out.println("No Course found with id=" + courseId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rsCourse.close();
                psCourse.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return courseInfo;
    }

    @Override
    public int[] getTutorSchedule(int tutorId) {
        String sqlTutorSchedule = "SELECT * FROM WeeklySchedule Where TutorID =?";
        PreparedStatement psTutorSchedule = null;
        ResultSet rsTutorSchedule = null;
        int[] tutorSchedule = new int[21];
        try {
            con = dataSource.getConnection();
            psTutorSchedule = con.prepareStatement(sqlTutorSchedule);
            psTutorSchedule.setString(1, String.valueOf(tutorId));
            rsTutorSchedule = psTutorSchedule.executeQuery();
            if (rsTutorSchedule.next()) {

                for (int i=3;i<24;i++){
                    tutorSchedule[i-3] = rsTutorSchedule.getInt(i);
                }

            } else {
                System.out.println("No Tutor found with id=" + tutorId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rsTutorSchedule.close();
                psTutorSchedule.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return tutorSchedule;
    }

    @Override
    public boolean saveRating(int tutorId ,String rating) {
        String sql = "UPDATE Tutor SET Rating=? WHERE ID=?";
        Connection con = null;
        PreparedStatement ps;
        float averageRating = calculateAverageRating(tutorId ,rating);

        try {
            con = dataSource.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, String.valueOf(averageRating));
            ps.setString(2, String.valueOf(tutorId));
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
    public boolean saveFeedback(String studentEmail, TutorProfileForm tutorProfileForm) {
        String sql = "INSERT INTO Review (StudentID, Text, Date, Rate, TutorID) VALUES (?,?,?,?,?)";
        Connection con = null;
        PreparedStatement ps;

        //https://www.mkyong.com/java/java-how-to-get-current-date-time-date-and-calender/
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String currentDate = dateFormat.format(date);

        int studentID = getStudentId(studentEmail);
        try {
            con = dataSource.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, String.valueOf(studentID));
            ps.setString(2, String.valueOf(tutorProfileForm.getFeedback()));
            ps.setString(3, String.valueOf(currentDate));
            ps.setString(4, String.valueOf(tutorProfileForm.getRating()));
            ps.setString(5, String.valueOf(tutorProfileForm.getId()));

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
    public float calculateAverageRating(int tutorId, String rating) {
        String sql = "SELECT * FROM Tutor Where ID =?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        float ratingCount;
        float oldRating;
        float newRating = 0;
        float tutorrating = Float.parseFloat(rating);
        try {
            con = dataSource.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, String.valueOf(tutorId));
            rs = ps.executeQuery();
            if (rs.next()) {
                ratingCount = Float.parseFloat(rs.getString("TotalRatings"));
                oldRating = Float.parseFloat(rs.getString("Rating"));
                //https://stackoverflow.com/questions/12636613/how-to-calculate-moving-average-without-keeping-the-count-and-data-total

                newRating = (oldRating * (ratingCount - 1) / ratingCount) + (tutorrating / ratingCount);

                increaseTotalRating(tutorId, ratingCount);

            } else {
                System.out.println("No Tutor found with id=" + tutorId);
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

        return newRating;
    }

    @Override
    public boolean increaseTotalRating(int tutorId, float ratingCount) {
        String sql = "UPDATE Tutor SET TotalRatings=? WHERE ID=?";
        Connection con = null;
        PreparedStatement ps;
        try {
            con = dataSource.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, String.valueOf(ratingCount+1));
            ps.setString(2, String.valueOf(tutorId));
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
