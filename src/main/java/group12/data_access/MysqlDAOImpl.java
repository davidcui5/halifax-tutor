package group12.data_access;


import group12.tutor_profile.TutorProfileForm;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Transactional
@Component
@ComponentScan
@ImportResource("classpath:spring.xml")
public class MysqlDAOImpl implements IDataAccessObject {

    Logger logger = LogManager.getLogger("Logger DB");

    private DataSource dataSource;
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public int countOfUserWithEmail(String email) {
        SQLOperationTemplate op = new NumberOfEmailSQLOperation(email);
        int numberOfEmails = (int) op.executeMysqlQuery();
        return numberOfEmails;
    }

    @Override
    public int countOfUserWithPhone(String phoneNumber) {
        SQLOperationTemplate op = new NumberOfPhoneSQLOperation(phoneNumber);
        int numberOfPhones = (int) op.executeMysqlQuery();
        return numberOfPhones;
    }

    @Override
    public int countOfUserWithCreditCardNum(String creditCardNum) {
        SQLOperationTemplate op = new NumberOfCreditCardSQLOperation(creditCardNum);
        int numberOfCards = (int) op.executeMysqlQuery();
        return numberOfCards;
    }

    @Override
    public int countOfActivationCodeWithValue(String codeValue) {
        SQLOperationTemplate op = new CheckActivationCodeSQLOperation(codeValue);
        if(op.executeMysqlQuery() != null){
            return 1;
        }
        else{
            return 0;
        }
    }

    @Override
    public boolean saveStudent(Student student) {
        SQLOperationTemplate op = new SaveStudentSQLOperation(student);
        return (Boolean) op.executeMysqlQuery();
    }

    @Override
    public Student getStudentByEmail(String email) {
        SQLOperationTemplate op = new GetStudentByEmailSQLOperation(email);
        Student student = (Student) op.executeMysqlQuery();
        return student;
    }

    @Override
    public Tutor getTutorByEmail(String email) {
        SQLOperationTemplate op = new GetTutorEmailSQLOperation(email);
        Tutor tutor = (Tutor) op.executeMysqlQuery();
        return tutor;
    }

    @Override
    public Admin getAdminByEmail(String email) {
        SQLOperationTemplate op = new GetAdminSQLOperation(email);
        return (Admin) op.executeMysqlQuery();
    }


    @Override
    public boolean saveTutor(Tutor tutor) {
        SQLOperationTemplate op = new SaveTutorSQLOperation(tutor);
        return (Boolean) op.executeMysqlQuery();
    }

    @Override
    public int getStudentIDByEmail(String email) {
        SQLOperationTemplate op = new GetStudentIdSQLOperation(email);
        Student student = (Student) op.executeMysqlQuery();
        return student.getStudentID();
    }

    @Override
    public int getTutorIDByEmail(String email) {
        SQLOperationTemplate op = new GetTutorIdSQLOperation(email);
        Tutor tutor = (Tutor) op.executeMysqlQuery();
        return tutor.getTutorID();
    }

    @Override
    public boolean saveActivationCode(String code) {
        SQLOperationTemplate op = new SaveActivationCodeSQLOperation(code);
        int result = (int) op.executeMysqlQuery();
        if (result == 1)
            return true;
        else
            return false;
    }

    @Override
    public boolean setStudentActivatedStatus(int id, boolean activateCode) {
        SQLOperationTemplate op =
                new SetStudentActivatedStatusSQLOperation(id, activateCode);
        int result = (int) op.executeMysqlQuery();
        if (result == 1)
            return true;
        else return false;
    }

    @Override
    public boolean setTutorActivatedStatus(int id, boolean activateCode) {
        SQLOperationTemplate op =
                new SetTutorActivatedStatusSQLOperation(id, activateCode);
        int result = (int) op.executeMysqlQuery();
        if (result == 1)
            return true;
        else
            return false;
    }

    @Override
    public boolean setStudentBannedStatus(int studentID, boolean status) {
        SQLOperationTemplate op =
                new SetStudentBannedStatusSQLOperation(studentID, status);
        return (Boolean) op.executeMysqlQuery();
    }

    @Override
    public boolean setTutorBannedStatus(int tutorID, boolean status) {
        SQLOperationTemplate op =
                new SetTutorBannedStatusSQLOperation(tutorID, status);
        return (Boolean) op.executeMysqlQuery();
    }

    @Override
    public boolean deleteActivationCodeByValue(String codeValue) {
        SQLOperationTemplate op =
                new DeleteActivationCodeByValueSQLOperation(codeValue);
        int result = (int) op.executeMysqlQuery();
        if (result == 1)
            return true;
        else return false;
    }

    @Override
    public boolean deleteStudent(int id) {
        SQLOperationTemplate op = new DeleteStudentSQLOperation(id);
        int result = (int) op.executeMysqlQuery();
        if (result == 1)
            return true;
        else return false;
    }

    @Override
    public boolean deleteTutor(int id) {
        SQLOperationTemplate op = new DeleteTutorSQLOperation(id);
        int result = (int) op.executeMysqlQuery();
        if (result == 1)
            return true;
        else return false;
    }

    @Override
    public boolean updateStudentPassword(String email, String newPassword) {
        SQLOperationTemplate op = new UpdateStudentPasswordSQLOperation(email, newPassword);
        int result = (int) op.executeMysqlQuery();
        if (result == 1)
            return true;
        else return false;
    }

    @Override
    public boolean updateTutorPassword(String email, String newPassword) {
        SQLOperationTemplate op =
                new UpdateTutorPasswordSQLOperation(email, newPassword);
        int result = (int) op.executeMysqlQuery();
        if (result == 1)
            return true;
        else return false;
    }

    @Override
    public boolean setCourseToTutor(int tutorId, int courseId, float price) {
        SQLOperationTemplate op = new SetCourseToTutorSQLOperation(tutorId, courseId, price);
        int result = (int) op.executeMysqlQuery();
        if (result == 1)
            return true;
        else
            return false;
    }

    @Override
    public List<Course> getCoursesOFTutor(int tutorId) {
        SQLOperationTemplate op = new GetCoursesOfTutorSQLOperation(tutorId);
        List<Course> courses = (List<Course>) op.executeMysqlQuery();
        return courses;
    }

    @Override
    public int numberOfCourse(String courseName) {
        SQLOperationTemplate op = new NumberOfCourseSQLOperation(courseName);
        int result = (int) op.executeMysqlQuery();
        return result;
    }

    @Override
    public ActivationCode checkActivationCode(String code) {
        SQLOperationTemplate op = new CheckActivationCodeSQLOperation(code);
        ActivationCode activationCode = (ActivationCode) op.executeMysqlQuery();
        return activationCode;
    }

    @Override
    public Course getCourseByName(String nameCourse) {
        SQLOperationTemplate op = new GetCourseByNameSQLOperation(nameCourse);
        Course course = (Course) op.executeMysqlQuery();
        return course;
    }

    @Override
    public List<Course> getAllCourses() {
        SQLOperationTemplate op = new GetAllCourseSQLOperation();
        List<Course> courseList = (List<Course>) op.executeMysqlQuery();
        return courseList;
    }

    @Override
    public boolean saveCourse(Course course) {
        SQLOperationTemplate op = new SaveCourseSQLOperation(course.getName(), course.getSchool());
        int result = (int) op.executeMysqlQuery();
        if (result == 1)
            return true;
        else
            return false;
    }
  
    @Override
    public boolean updateStudentEmail(String oldMail, String newMail) {
        UpdateStudentEmailSQLOperation updateStudentEmailSQLOperation =
                new UpdateStudentEmailSQLOperation(oldMail, newMail);
        int result = (int) updateStudentEmailSQLOperation.executeMysqlQuery();
        if (result == 1)
            return true;
        else
            return false;
    }

    @Override
    public boolean updateStudentPhone(String email, String newPhone) {
        UpdateStudentPhoneSQLOperation updateStudentPhoneSQLOperation =
                new UpdateStudentPhoneSQLOperation(email, newPhone);
        int result = (int) updateStudentPhoneSQLOperation.executeMysqlQuery();
        if (result == 1)
            return true;
        else
            return false;
    }

    @Override
    public int getStudentActivationStatus(String email) {
        getActivationStudentStatusSQLOperation getActivationStudentStatusSQLOperation = new getActivationStudentStatusSQLOperation(email);
        int result = (int) getActivationStudentStatusSQLOperation.executeMysqlQuery();
        return result;
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
                assert (rsTutor!=null);
                assert (psTutor!=null);
                assert (con!=null);
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
                assert (rsTutorCourse!=null);
                assert (psTutorCourse!=null);
                assert (con!=null);
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
                assert (rsCourse!=null);
                assert (psCourse!=null);
                assert(con!=null);
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
                assert (rsTutorSchedule!=null);
                assert (psTutorSchedule!=null);
                assert(con!=null);
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
            assert (ps!=null);
            ps.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            if (con != null) {
                try {

                    assert(con!=null);
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

//        int studentID = getStudentId(studentEmail); TODO: Remove
        int studentID = 869;

        try {
            con = dataSource.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, String.valueOf(studentID));
            ps.setString(2, String.valueOf(tutorProfileForm.getFeedback()));
            ps.setString(3, String.valueOf(currentDate));
            ps.setString(4, String.valueOf(tutorProfileForm.getRating()));
            ps.setString(5, String.valueOf(tutorProfileForm.getId()));

            ps.executeUpdate();
            assert (ps!=null);
            ps.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            if (con != null) {
                try {
                    assert (con!=null);
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
                assert (rs!=null);
                assert (ps!=null);
                assert (con!=null);
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
            assert (ps!=null);
            ps.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            if (con != null) {
                try {
                    assert (con!=null);
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
