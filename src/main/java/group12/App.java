package group12;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;

@SpringBootApplication
@ComponentScan
@ImportResource("classpath:spring.xml")
public class App {

    public static void main(String[] args) throws Exception {

        SpringApplication.run(App.class, args);
    }
}