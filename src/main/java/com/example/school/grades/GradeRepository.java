package com.example.school.grades;

import com.example.school.users.User;
import com.example.school.courses.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {
    Optional<Grade> findGradeById(Long id);

    Optional<Grade> findGradeByCourseAndStudent(Course courseCode, User userId);
}
