package group12.dataaccess;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TutorPublicInfoTest {
    private static final float DELTA = 1e-15f;
    private TutorPublicInfo tutorPublicInfo;

    @Before
    public void testSetup() {
        tutorPublicInfo = new TutorPublicInfo(0, "", "", "", "",
                0.0f, 0.0f, false);
    }

    @Test
    public void testGetPhotoURL() {
        String expected = "";
        assertEquals(expected, tutorPublicInfo.getPhotoURL());
    }

    @Test
    public void testGetFirstName() {
        String expected = "";
        assertEquals(expected, tutorPublicInfo.getFirstName());
    }

    @Test
    public void testGetLastName() {
        String expected = "";
        assertEquals(expected, tutorPublicInfo.getLastName());
    }

    @Test
    public void testGetEducation() {
        String expected = "";
        assertEquals(expected, tutorPublicInfo.getEducation());
    }

    @Test
    public void testGetRating() {
        float expected = 0.0f;
        assertEquals(expected, tutorPublicInfo.getRating(), DELTA);
    }

    @Test
    public void testGetPricePerHour() {
        float expected = 0.0f;
        assertEquals(expected, tutorPublicInfo.getPricePerHour(), DELTA);
    }

    @Test
    public void testSetPhotoURL() {
        String photoURL = "fakeURL";
        String previous = tutorPublicInfo.getPhotoURL();
        tutorPublicInfo.setPhotoURL(photoURL);
        assertNotEquals(previous, tutorPublicInfo.getPhotoURL());
        assertEquals(photoURL, tutorPublicInfo.getPhotoURL());
    }

    @Test
    public void testSetFirstName() {
        String firstName = "zongming";
        String previous = tutorPublicInfo.getFirstName();
        tutorPublicInfo.setFirstName(firstName);
        assertNotEquals(previous, tutorPublicInfo.getFirstName());
        assertEquals(firstName, tutorPublicInfo.getFirstName());
    }

    @Test
    public void testSetLastName() {
        String lastName = "liu";
        String previous = tutorPublicInfo.getLastName();
        tutorPublicInfo.setLastName(lastName);
        assertNotEquals(previous, tutorPublicInfo.getLastName());
        assertEquals(lastName, tutorPublicInfo.getLastName());
    }

    @Test
    public void testSetEducation() {
        String education = "dalhousie";
        String previous = tutorPublicInfo.getEducation();
        tutorPublicInfo.setEducation(education);
        assertNotEquals(previous, tutorPublicInfo.getEducation());
        assertEquals(education, tutorPublicInfo.getEducation());
    }

    @Test
    public void testSetRating() {
        float rating = 4.0f;
        float previous = tutorPublicInfo.getRating();
        tutorPublicInfo.setRating(rating);
        assertNotEquals(previous, tutorPublicInfo.getRating());
        assertEquals(rating, tutorPublicInfo.getRating(), DELTA);
    }

    @Test
    public void testSetPricePerHour() {
        float pricePerHour = 4.0f;
        float previous = tutorPublicInfo.getPricePerHour();
        tutorPublicInfo.setPricePerHour(pricePerHour);
        assertNotEquals(previous, tutorPublicInfo.getPricePerHour());
        assertEquals(pricePerHour, tutorPublicInfo.getPricePerHour(), DELTA);
    }

    @After
    public void testTeardown() {
        tutorPublicInfo = null;
    }
}
