package maksym.durov.config;

import java.util.Scanner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
        "maksym.durov.dao",
        "maksym.durov.controller",
        "maksym.durov.service",
        "maksym.durov.mappers",
        "maksym.durov.filter"
})
public class AppConfig {

    @Bean
    public Scanner getScanner() {
        return new Scanner(System.in);
    }
}
