package group12.data_access;

import java.util.List;

public interface IDataAccessObject {

    Student getStudentByEmail(String email);

    Tutor getTutorByEmail(String email);

    Admin getAdminByEmail(String email);

    ActivationCode checkActivationCode(String code);

    Course getCourseByName(String nameCourse);

    List<Course> getAllCourses();

    int getStudentIDByEmail(String email);

    int getTutorIDByEmail(String email);

    int countOfUserWithEmail(String email);

    int countOfUserWithPhone(String phone);

    int countOfUserWithCreditCardNum(String cardNum);

    int countOfActivationCodeWithValue(String codeValue);

    boolean saveActivationCode(String code);

    boolean saveCourse(Course course);

    boolean saveStudent(Student student);

    boolean saveTutor(Tutor tutor);

    boolean setStudentActivatedStatus(int studentID, boolean status);

    boolean setTutorActivatedStatus(int tutorID, boolean status);

    boolean setStudentBannedStatus(int studentID, boolean status);

    boolean setTutorBannedStatus(int tutorID, boolean status);


    boolean deleteActivationCodeByValue(String codeValue);

    boolean deleteStudent(int studentID);

    boolean deleteTutor(int tutorID);

    boolean updateStudentPassword(String studentEmail, String newPassword);

    boolean updateTutorPassword(String tutorEmail, String newPassword);

    boolean setCourseToTutor(int tutorId, int courseId, float price);

    List<Course> getCoursesOFTutor(int tutorId);

    int numberOfCourse(String courseName);

    boolean updateStudentEmail(String oldMail, String newMail);

    boolean updateStudentPhone(String email, String newPhone);
}
