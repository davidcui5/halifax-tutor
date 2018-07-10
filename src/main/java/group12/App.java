package group12;

import group12.logging.GlobalHandler;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@EnableAutoConfiguration
public class App extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(App.class);
    }

    public static void main(String[] args) throws Exception {
        GlobalHandler globalHandler = new GlobalHandler();
        Thread.setDefaultUncaughtExceptionHandler(globalHandler);
        SpringApplication.run(App.class, args);
    }
}
