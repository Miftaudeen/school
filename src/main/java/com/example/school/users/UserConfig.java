package com.example.school.users;

import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class UserConfig {
    @Bean
    CommandLineRunner userCommandLineRunner(UserRepository repository){
        return args -> {
            User kola = new User("Kolade Otitoju", "okolade@gmail.com", LocalDate.of(2001, Month.AUGUST, 2));
            User idris = new User("Idris Yusuf", "idrisyusuf@gmail.com", LocalDate.of(1994, Month.JANUARY, 2));
            repository.saveAll(List.of(kola, idris));
        };
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
