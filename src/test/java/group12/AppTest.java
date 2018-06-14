package group12;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static junit.framework.TestCase.assertTrue;

public class AppTest {

    @Test
    public void testDBConnection() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        DBDAO dbda = context.getBean("studentDAOJDBCTemplate", DBDAO.class);
        assertTrue(dbda.authorizeStudent("zaher88abd@gmail.com", "zaher"));
    }
}
