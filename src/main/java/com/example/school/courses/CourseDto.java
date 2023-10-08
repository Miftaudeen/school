package com.example.school.courses;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
public class CourseDto {
    @NotNull
    private String title;
    @NotNull
    private Integer units;
}
