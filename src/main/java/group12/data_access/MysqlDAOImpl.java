package group12.data_access;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

@Transactional
@Component
@ComponentScan
@ImportResource("classpath:spring.xml")
public class MysqlDAOImpl implements IDataAccessObject {

    PreparedStatement ps = null;
    ResultSet rs = null;

    @Override
    public int countOfUserWithEmail(String email) {
        NumberOfEmailSQLOperation numberOfEmailSQLOperation = new NumberOfEmailSQLOperation(email);
        int numberOfEmails = (int) numberOfEmailSQLOperation.executeMysqlQuery();
        return numberOfEmails;
    }

    @Override
    public int countOfUserWithPhone(String phoneNumber) {
        NumberOfPhoneSQLOperation numberOfPhoneSQLOperation = new NumberOfPhoneSQLOperation(phoneNumber);
        int numberOfPhones = (int) numberOfPhoneSQLOperation.executeMysqlQuery();
        return numberOfPhones;
    }

    @Override
    public int countOfUserWithCreditCardNum(String creditCardNum) {
        NumberOfCreditCardSQLOperation numberOfCreditCardSQLOperation = new NumberOfCreditCardSQLOperation(creditCardNum);
        int numberofCards = (int) numberOfCreditCardSQLOperation.executeMysqlQuery();
        return numberofCards;
    }

    @Override
    public int countOfActivationCodeWithValue(String codeValue) {
        return 0;
    }

    @Override
    public boolean saveStudent(Student student) {
        SaveStudentSQLOperation regStudent = new SaveStudentSQLOperation(student.getFirstName(), student.getLastName()
                , student.getEmail(), student.getPassword(), student.getSchool(), student.getPhoneNumber());
        int result = (int) regStudent.executeMysqlQuery();
        if (result == 1)
            return true;
        else
            return false;
    }

    @Override
    public Student getStudentByEmail(String email) {
        GetStudentByEmailSQLOperation getStudentByEmailSQLOperation = new GetStudentByEmailSQLOperation(email);
        Student student = (Student) getStudentByEmailSQLOperation.executeMysqlQuery();
        return student;
    }

    @Override
    public Tutor getTutorByEmail(String email) {
        GeTTutorEmailSQLOperation authorizeTutorSQLOperation = new GeTTutorEmailSQLOperation(email);
        Tutor tutor = (Tutor) authorizeTutorSQLOperation.executeMysqlQuery();
        return tutor;
    }

    @Override
    public Admin getAdminByEmail(String email) {
        return null;
    }


    @Override
    public boolean saveTutor(Tutor tutor) {
        SaveTutorSQLOperation saveTutorSQLOperation = new SaveTutorSQLOperation(tutor.getFirstName(), tutor.getLastName()
                , tutor.getEmail(), tutor.getPassword(), tutor.getPhoneNumber(), tutor.getCreditCardHolder()
                , tutor.getCreditCardNum(), tutor.getExpiryDate(), tutor.getSecurityCode());
        int result = (int) saveTutorSQLOperation.executeMysqlQuery();
        if (result == 1)
            return true;
        else return false;
    }

    @Override
    public int getStudentIDByEmail(String email) {
        GetStudentIdSQLOperation getStudentIdSQLOperation = new GetStudentIdSQLOperation(email);
        Student student = (Student) getStudentIdSQLOperation.executeMysqlQuery();
        return student.getStudentID();
    }

    @Override
    public int getTutorIDByEmail(String email) {
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
    public boolean setStudentActivatedStatus(int id, boolean activateCode) {
        SetStudentActivatedStatusSQLOperation setStudentActivatedStatusSQLOperation =
                new SetStudentActivatedStatusSQLOperation(id, activateCode);
        int result = (int) setStudentActivatedStatusSQLOperation.executeMysqlQuery();
        if (result == 1)
            return true;
        else return false;
    }

    @Override
    public boolean setTutorActivatedStatus(int id, boolean activateCode) {
        SetTutorActivatedStatusSQLOperation setTutorActivatedStatusSQLOperation =
                new SetTutorActivatedStatusSQLOperation(id, activateCode);
        int result = (int) setTutorActivatedStatusSQLOperation.executeMysqlQuery();
        if (result == 1)
            return true;
        else
            return false;
    }

    @Override
    public boolean setActivatedStatusByEmail(String email, boolean status) {
        return false;
    }

    @Override
    public boolean setStudentBannedStatus(int studentID, boolean status) {
        SetStudentBannedStatusSQLOperation setStudentBannedStatus =
                new SetStudentBannedStatusSQLOperation(studentID, status);
        int result = (int) setStudentBannedStatus.executeMysqlQuery();
        if (result == 1)
            return true;
        else
            return false;
    }

    @Override
    public boolean setTutorBannedStatus(int tutorID, boolean status) {
        SetTutorBannedStatusSQLOperation setStudentBannedStatus =
                new SetTutorBannedStatusSQLOperation(tutorID, status);
        int result = (int) setStudentBannedStatus.executeMysqlQuery();
        if (result == 1)
            return true;
        else
            return false;
    }

    @Override
    public boolean setBannedStatusByEmail(String email, boolean status) {
        return false;
    }

    @Override
    public boolean deleteActivationCodeByValue(String codeValue) {
        DeleteActivationCodeByValueSQLOperation deleteActivationCodeByValueSQLOperation =
                new DeleteActivationCodeByValueSQLOperation(codeValue);
        int result = (int) deleteActivationCodeByValueSQLOperation.executeMysqlQuery();
        if (result == 1)
            return true;
        else return false;
    }

    @Override
    public boolean deleteStudent(int id) {
        DeleteStudentSQLOperation deleteStudentSQLOperation = new DeleteStudentSQLOperation(id);
        int result = (int) deleteStudentSQLOperation.executeMysqlQuery();
        if (result == 1)
            return true;
        else return false;
    }

    @Override
    public boolean deleteTutor(int id) {
        DeleteTutorSQLOperation deleteTutorSQLOperation = new DeleteTutorSQLOperation(id);
        int result = (int) deleteTutorSQLOperation.executeMysqlQuery();
        if (result == 1)
            return true;
        else return false;
    }

    @Override
    public boolean updateStudentPassword(String email, String newPassword) {
        UpdateStudentPasswordSQLOperation updateStudentPasswordSQLOperation = new UpdateStudentPasswordSQLOperation(email, newPassword);
        int result = (int) updateStudentPasswordSQLOperation.executeMysqlQuery();
        if (result == 1)
            return true;
        else return false;
    }

    @Override
    public boolean updateTutorPassword(String email, String newPassword) {
        UpdateTutorPasswordSQLOperation updateTutorPasswordSQLOperation =
                new UpdateTutorPasswordSQLOperation(email, newPassword);
        int result = (int) updateTutorPasswordSQLOperation.executeMysqlQuery();
        if (result == 1)
            return true;
        else return false;
    }

    @Override
    public boolean setCourseToTutor(int tutorId, int courseId, float price) {
        SetCourseToTutorSQLOperation setCourseToTutorSQLOperation = new SetCourseToTutorSQLOperation(tutorId, courseId, price);
        int result = (int) setCourseToTutorSQLOperation.executeMysqlQuery();
        if (result == 1)
            return true;
        else
            return false;
    }

    @Override
    public ActivationCode cheActivationCode(String code) {
        CheckActivationCodeSQLOperation activationCodeSQLOperation = new CheckActivationCodeSQLOperation(code);
        ActivationCode activationCode = (ActivationCode) activationCodeSQLOperation.executeMysqlQuery();
        return activationCode;
    }

    @Override
    public Course getCourseByName(String nameCourse) {
        GetCourseByNameSQLOperation getCourseByNameSQLOperation = new GetCourseByNameSQLOperation(nameCourse);
        Course course = (Course) getCourseByNameSQLOperation.executeMysqlQuery();
        return course;
    }

    @Override
    public List<Course> getAllCourses() {
        GetAllCourseSQLOperation getAllCourseSQLOperation = new GetAllCourseSQLOperation();

        return null;
    }

    @Override
    public boolean saveCourse(Course course) {
        SaveCourseSQLOperation saveCourseSQLOperation = new SaveCourseSQLOperation(course);
        int result = (int) saveCourseSQLOperation.executeMysqlQuery();
        if (result == 1)
            return true;
        else
            return false;
    }
}
