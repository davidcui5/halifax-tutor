package group12.data_access;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class GetStudentDatabaseOperationTest {

    static ClassPathXmlApplicationContext context;
    static DatabaseOperationTemplate op;

    @BeforeClass
    public static void configDBConnection() {
        context = new ClassPathXmlApplicationContext("spring.xml");
        op = context.getBean("op", GetStudentDatabaseOperation.class);
    }

    @Test
    public void testExecuteQuery(){
        Student student = (Student) op.executeMysqlQuery("yq50649@dal.ca");
        System.out.println(student.getPassword());
        assertEquals("CE1C1CDC2FAC8E1167F22CD4BD88D324", student.getPassword());
        assertFalse(student.isActivated());
        assertFalse(student.isBanned());
    }
}
