package com.example.school.courses;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CourseConfig {
    @Bean
    CommandLineRunner commandLineRunner(CourseRepository repository){
        return args -> {
            Course maths = new Course("Engineering Maths", "MTH201", 5);
            Course english = new Course("Use of English", "ENG201", 2);
            repository.saveAll(List.of(maths, english));
        };
    }
}
