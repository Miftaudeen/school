package com.example.school.grades;

import com.example.school.courses.Course;
import com.example.school.users.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class GradeConfig {
    @Bean
    CommandLineRunner commandLineRunner(GradeRepository repository){
        return args -> {
            Grade maths = new Grade(69, new Course(1L, "Engineering Maths", "MTH201", 5), new User());
            Grade english = new Grade(90, new Course(), new User());
            repository.saveAll(List.of(maths, english));
        };
    }
}
