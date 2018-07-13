package group12.data_access;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class GetStudentSQLOperationTest {

    static ClassPathXmlApplicationContext context;
    static DataSource ds;

    @BeforeClass
    public static void configDBConnection() {
        context = new ClassPathXmlApplicationContext("spring.xml");
        ds = context.getBean("dataSource", org.springframework.jdbc.datasource.DriverManagerDataSource.class);
    }

    @Test
    public void testExecuteQuery(){
        SQLOperationTemplate op = new GetStudentSQLOperation("yq50649@dal.ca");
        op.setDataSource(ds);
        Student student = (Student) op.executeMysqlQuery();
        System.out.println(student.getPassword());
        assertEquals("CE1C1CDC2FAC8E1167F22CD4BD88D324", student.getPassword());
        assertFalse(student.isActivated());
        assertFalse(student.isBanned());
    }
}
