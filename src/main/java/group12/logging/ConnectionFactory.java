package group12.logging;

import java.sql.Connection;
import java.sql.SQLException;


import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class ConnectionFactory {

    private static ApplicationContext context;

    private static BasicDataSource dataSource;

    public void setDataSource(BasicDataSource theDataSource) {
        dataSource = theDataSource;
    }

    public BasicDataSource getDataSource() {
        return dataSource;
    }


    public static Connection getDatabaseConnection() throws SQLException {
        if (context == null) {
            context = new ClassPathXmlApplicationContext("applicationContext.xml");
        }
        if (dataSource == null) {
            dataSource = (BasicDataSource) context.getBean("dataSource");
        }
        return dataSource.getConnection();
    }
}
