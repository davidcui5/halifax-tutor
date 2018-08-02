package group12.registration;

import group12.data_access.*;
import group12.tutor_profile.TutorProfileForm;

import java.util.ArrayList;
import java.util.List;

public class dataAccessObjectMock implements IDataAccessObject {
    @Override
    public int countOfUserWithEmail(String email) {
        if(email.equals("new@email.com")){
            return 0;
        }
        else{
            return 1;
        }
    }

    @Override
    public int countOfUserWithPhone(String phone) {
        if(phone.equals("1234567890")){
            return 0;
        }
        else{
            return 1;
        }
    }

    @Override
    public int countOfUserWithCreditCardNum(String cardNum) {
        if(cardNum.equals("1234567812345678")){
            return 0;
        }
        else{
            return 1;
        }
    }

    @Override
    public boolean saveStudent(Student student) {
        if(student.getPassword().equals("saveSuccess")){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public boolean saveTutor(Tutor tutor) {
        if(tutor.getPassword().equals("saveSuccess")){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public int getStudentIDByEmail(String email) {
        return 100;
    }

    @Override
    public int getTutorIDByEmail(String email) {
        return 100;
    }

    @Override
    public boolean saveActivationCode(String code) {
        return true;
    }

    @Override
    public ActivationCode checkActivationCode(String code) {
        if(code.equals("validCode")){
            return new ActivationCode();
        }
        else{
            return null;
        }
    }

    @Override
    public boolean setStudentActivatedStatus(int studentID, boolean status) {
        if(studentID == 100){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public boolean setTutorActivatedStatus(int tutorID, boolean status) {
        if(tutorID == 100){
            return true;
        }
        else{
            return false;
        }
    }


    //unused for testing registration
    @Override
    public Student getStudentByEmail(String email) {
        return null;
    }

    @Override
    public Tutor getTutorByEmail(String email) {
        return null;
    }

    @Override
    public Course getCourseByName(String nameCourse) {
        return null;
    }

    @Override
    public List<Course> getAllCourses() {
        return null;
    }

    @Override
    public boolean saveCourse(Course course) {
        return false;
    }

    @Override
    public boolean deleteActivationCodeByValue(String codeValue) {
        return false;
    }

    @Override
    public boolean deleteStudent(int studentID) {
        return false;
    }

    @Override
    public boolean deleteTutor(int tutorID) {
        return false;
    }

    @Override
    public boolean updateStudentPassword(String studentEmail, String newPassword) {
        return false;
    }

    @Override
    public boolean updateTutorPassword(String tutorEmail, String newPassword) {
        return false;
    }

    @Override
    public boolean setCourseToTutor(int tutorId, int courseId, float price) {
        return false;
    }

    @Override
    public List<Course> getCoursesOFTutor(int tutorId) {
        return null;
    }

    @Override
    public int numberOfCourse(String courseName) {
        return 0;
    }

    @Override
    public boolean updateStudentEmail(String oldMail, String newMail) {
        return false;
    }

    @Override
    public boolean updateStudentPhone(String email, String newPhone) {
        return false;
    }

    @Override
    public int getStudentActivationStatus(String email) {
        return 0;
    }

    @Override
    public TutorProfileForm getTutorProfile(int tutorId) {
        return null;
    }

    @Override
    public String[] getTutorInfo(int tutorId) {
        return new String[0];
    }

    @Override
    public int[] getTutorSchedule(int tutorId) {
        return new int[0];
    }

    @Override
    public boolean saveRating(int tutorId, String rating) {
        return false;
    }

    @Override
    public boolean saveFeedback(String studentEmail, TutorProfileForm tutorProfileForm) {
        return false;
    }

    @Override
    public float calculateAverageRating(int tutorId, String rating) {
        return 0;
    }

    @Override
    public boolean increaseTotalRating(int tutorId, float ratingCount) {
        return false;
    }

    @Override
    public boolean getSearchAuthConf() {
        return false;
    }
}
