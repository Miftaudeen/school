package com.example.school.grades;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {
    Optional<Grade> findGradeByCourseCodeAndStudentId(String code, Long userId);
    Optional<Grade> findGradeById(Long id);
}
