package group12;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.*;

import static junit.framework.TestCase.assertTrue;

public class AppTest {

    @Test
    public void testRegStudent() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        DBDAO dbda = context.getBean("DBDAO", DBDAO.class);
        Student student = new Student();
        student.setEmail("zaher88abd@gmail.com");
        student.setFirstName("zaher");
        student.setLastName("Abd");
        student.setSchool("Dal");
        student.setPhoneNumber("902212300");
        assertTrue(dbda.regStudent(student));
    }

    @Test
    public void testRegTutor() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        DBDAO dbda = context.getBean("DBDAO", DBDAO.class);
        Tutor tutor = new Tutor();
        tutor.setEmail("zaher88abd@gmail.com");
        tutor.setFirstName("zaher");
        tutor.setLastName("Abd");
        tutor.setPhoneNumber("902212300");
        assertTrue(dbda.regTutor(tutor));
    }

}
