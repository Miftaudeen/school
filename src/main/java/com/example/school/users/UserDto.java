package com.example.school.users;

import com.example.school.grades.GradeDto;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
@Data
@NoArgsConstructor
public class UserDto {
    private static final DateTimeFormatter dateFormat
            = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String email;
    @NotNull
    private String dob;
    public LocalDate getDob() {
        return LocalDate.parse(dob, dateFormat);
    }

    public void setDob(LocalDate date) {
        this.dob = dateFormat.format(date);
    }

}
