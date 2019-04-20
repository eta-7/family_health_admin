package zkt.health;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@Configuration
@EnableSpringDataWebSupport
public class FamilyHealthApplication {

    public static void main(String[] args) {
        SpringApplication.run(FamilyHealthApplication.class, args);
    }
}

