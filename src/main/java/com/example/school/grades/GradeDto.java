package com.example.school.grades;

import com.example.school.courses.Course;
import com.example.school.courses.CourseService;
import com.example.school.users.User;
import com.example.school.users.UserService;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GradeDto {
    @NotNull
    private int score;
    @NotNull
    private String course;
    @NotNull
    private Long user;
}