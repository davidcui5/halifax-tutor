package group12.data_access;


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
import java.util.List;

@Transactional
@Component
@ComponentScan
@ImportResource("classpath:spring.xml")
public class MysqlDAOImpl implements IDataAccessObject {

    Logger logger = LogManager.getLogger("Logger DB");

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
}
