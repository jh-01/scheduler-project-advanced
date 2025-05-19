package org.example.scheduleadvanced;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ScheduleAdvancedApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScheduleAdvancedApplication.class, args);
    }

}
