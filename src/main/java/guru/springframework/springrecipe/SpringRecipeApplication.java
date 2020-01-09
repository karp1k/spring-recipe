package guru.springframework.springrecipe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringRecipeApplication {
    //uri: h2-console
    // jdbc:h2:mem:testdb
    public static void main(String[] args) {
        SpringApplication.run(SpringRecipeApplication.class, args);
    }

}
