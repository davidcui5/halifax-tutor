package group12;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@EnableAutoConfiguration
public class App extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(App.class);
    }

    public static void main(String[] args) throws Exception {
//        SpringApplication.run(App.class, args);
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        StudentsDAO dbda = context.getBean("studentDAOJDBCTemplate", StudentsDAO.class);
        System.out.println(dbda.authorizeStudent("zaher88abd@gmail.com", "zaher"));
    }
}