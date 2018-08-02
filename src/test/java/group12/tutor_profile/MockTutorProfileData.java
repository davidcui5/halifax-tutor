package group12.tutor_profile;

import group12.data_access.*;

import java.util.ArrayList;
import java.util.List;

public class MockTutorProfileData implements IDataAccessObject {
    @Override
    public Student getStudentByEmail(String email) {
        return null;
    }

    @Override
    public Tutor getTutorByEmail(String email) {
        return null;
    }

    @Override
    public ActivationCode checkActivationCode(String code) {
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
    public int getStudentIDByEmail(String email) {
        return 0;
    }

    @Override
    public int getTutorIDByEmail(String email) {
        return 0;
    }

    @Override
    public int countOfUserWithEmail(String email) {
        return 0;
    }

    @Override
    public int countOfUserWithPhone(String phone) {
        return 0;
    }

    @Override
    public int countOfUserWithCreditCardNum(String cardNum) {
        return 0;
    }

    @Override
    public boolean saveActivationCode(String code) {
        return false;
    }

    @Override
    public boolean saveCourse(Course course) {
        return false;
    }

    @Override
    public boolean saveStudent(Student student) {
        return false;
    }

    @Override
    public boolean saveTutor(Tutor tutor) {
        return false;
    }

    @Override
    public boolean setStudentActivatedStatus(int studentID, boolean status) {
        return false;
    }

    @Override
    public boolean setTutorActivatedStatus(int tutorID, boolean status) {
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
        TutorProfileForm tutorProfileForm = new TutorProfileForm();

        if (tutorId == 437) {

            ArrayList<String[]> courses = new ArrayList<>();
            String[] array = {"Test1", "Test2"};
            courses.add(array);
            int[] schedule = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21};

            tutorProfileForm.setPhotoURL("url");
            tutorProfileForm.setFirstName("rahul");
            tutorProfileForm.setLastName("vala");
            tutorProfileForm.setPhoneNumber("9028189696");
            tutorProfileForm.setEmail("rahulvala1502@gmail.com");
            tutorProfileForm.setEducation("Dalhousie University");
            tutorProfileForm.setExperience("3 Years");
            tutorProfileForm.setRating("4.5");
            tutorProfileForm.setCourseList(courses);
            tutorProfileForm.setTutorSchedule(schedule);
        }


        return tutorProfileForm;

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
        return true;
    }

    @Override
    public boolean saveFeedback(String studentEmail, TutorProfileForm tutorProfileForm) {
        return true;
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
