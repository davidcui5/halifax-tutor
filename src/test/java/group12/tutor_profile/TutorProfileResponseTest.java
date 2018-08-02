package group12.tutor_profile;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class TutorProfileResponseTest {

    private TutorProfileResponse response = new TutorProfileResponse();

    @Test
    public void testResult(){
        String result = "Success";
        response.setResult("Success");
        assertEquals(result, response.getResult());
    }

    @Test
    public void testDetails(){
        ArrayList<String> details = new ArrayList<>();
        details.add("Test1");
        response.addDetail("Test1");
        assertEquals(details, response.getDetails());
    }

    @Test
    public void testPhotoURL(){
        String result = "test url";
        response.setPhotoURL("test url");
        assertEquals(result, response.getPhotoURL());
    }

    @Test
    public void testFirstName(){
        String result = "test First Name";
        response.setFirstName("test First Name");
        assertEquals(result, response.getFirstName());
    }

    @Test
    public void testLastName(){
        String result = "test Last Name";
        response.setLastName("test Last Name");
        assertEquals(result, response.getLastName());
    }

    @Test
    public void testPhoneNumber(){
        String result = "9029029999";
        response.setPhoneNumber("9029029999");
        assertEquals(result, response.getPhoneNumber());
    }

    @Test
    public void testEmail(){
        String result = "rh318779@dal.ca";
        response.setEmail("rh318779@dal.ca");
        assertEquals(result, response.getEmail());
    }

    @Test
    public void testEducation(){
        String result = "test Education";
        response.setEducation("test Education");
        assertEquals(result, response.getEducation());
    }

    @Test
    public void testRating(){
        String result = "4.5";
        response.setRating("4.5");
        assertEquals(result, response.getRating());
    }

    @Test
    public void testExperience(){
        String result = "4 years";
        response.setExperience("4 years");
        assertEquals(result, response.getExperience());
    }

    @Test
    public void testCourseList(){
        ArrayList<String[]> courses = new ArrayList<>();
        String[] array = {"Test1", "Test2"};
        courses.add(array);
        response.setCourseList(courses);
        assertEquals(courses, response.getCourseList());
    }

    @Test
    public void testTutorSchedule(){
        int[] schedule = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21};
        response.setTutorSchedule(schedule);
        assertEquals(schedule, response.getTutorSchedule());
    }

}
