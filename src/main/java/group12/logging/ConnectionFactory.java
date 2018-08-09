package group12.logging;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class ConnectionFactory {

    private interface Singleton {
        ConnectionFactory INSTANCE = new ConnectionFactory();
    }

    private final BasicDataSource dataSource;

    private ConnectionFactory() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        dataSource = (BasicDataSource) context.getBean("dataSource");
    }

    public static Connection getDatabaseConnection() throws SQLException {
        return Singleton.INSTANCE.dataSource.getConnection();
    }
}
