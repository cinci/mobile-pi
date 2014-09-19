package pi4droid.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Main class for Spring Boot
 * <p>
 * Created by jc on 18. 9. 2014.
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Application {

    public static final String PROFILE_PI = "pi";
    public static final String PROFILE_DEV = "dev";

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        app.setAdditionalProfiles(PROFILE_DEV);
        app.run(args);
    }
}
