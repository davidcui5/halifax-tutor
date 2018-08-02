package group12.TutorProfile;

import group12.tutorprofile.TutorProfileForm;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class TutorProfileFormTest {

    private TutorProfileForm form = new TutorProfileForm();

    @Test
    public void testEmailToken(){
        String result = "test email token";
        form.setEmailToken("test email token");
        assertEquals(result, form.getEmailToken());
    }

    @Test
    public void testId(){
        int result = 234;
        form.setId(234);
        assertEquals(result, form.getId());
    }

    @Test
    public void testMessage(){
        String result = "test message";
        form.setMessage("test message");
        assertEquals(result, form.getMessage());
    }

    @Test
    public void testPhotoURL(){
        String result = "test url";
        form.setPhotoURL("test url");
        assertEquals(result, form.getPhotoURL());
    }

    @Test
    public void testFirstName(){
        String result = "test First Name";
        form.setFirstName("test First Name");
        assertEquals(result, form.getFirstName());
    }

    @Test
    public void testLastName(){
        String result = "test Last Name";
        form.setLastName("test Last Name");
        assertEquals(result, form.getLastName());
    }

    @Test
    public void testPhoneNumber(){
        String result = "9029029999";
        form.setPhoneNumber("9029029999");
        assertEquals(result, form.getPhoneNumber());
    }

    @Test
    public void testEmail(){
        String result = "rh318779@dal.ca";
        form.setEmail("rh318779@dal.ca");
        assertEquals(result, form.getEmail());
    }

    @Test
    public void testEducation(){
        String result = "test Education";
        form.setEducation("test Education");
        assertEquals(result, form.getEducation());
    }

    @Test
    public void testRating(){
        String result = "4.5";
        form.setRating("4.5");
        assertEquals(result, form.getRating());
    }

    @Test
    public void testFeedback(){
        String result = "test Feedback";
        form.setFeedback("test Feedback");
        assertEquals(result, form.getFeedback());
    }

    @Test
    public void testExperience(){
        String result = "4 years";
        form.setExperience("4 years");
        assertEquals(result, form.getExperience());
    }

    @Test
    public void testCourseList(){
        ArrayList<String[]> courses = new ArrayList<>();
        String[] array = {"Test1", "Test2"};
        courses.add(array);
        form.setCourseList(courses);
        assertEquals(courses, form.getCourseList());
    }

    @Test
    public void testTutorSchedule(){
        int[] schedule = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21};
        form.setTutorSchedule(schedule);
        assertEquals(schedule, form.getTutorSchedule());
    }

}
