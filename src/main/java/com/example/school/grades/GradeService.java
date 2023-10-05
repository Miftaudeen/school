package com.example.school.grades;

import com.example.school.courses.CourseRepository;
import com.example.school.users.User;
import com.example.school.courses.Course;
import com.example.school.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class GradeService {
    private final GradeRepository gradeRepository;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    @Autowired
    public GradeService(GradeRepository gradeRepository, CourseRepository courseRepository, UserRepository userRepository) {
        this.gradeRepository = gradeRepository;
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    public List<Grade> getGrades() {
        return gradeRepository.findAll();
    }

    public void addNewGrade(Grade grade) {
        Optional<Grade> optionalGrade = gradeRepository.findGradeById(grade.getId());
        if (optionalGrade.isPresent()){
            throw new IllegalArgumentException("Grade Code already in use");
        }
        gradeRepository.save(grade);
    }

    public void deleteGrade(Long gradeId) {
        boolean exists = gradeRepository.existsById(gradeId);
        if (!exists){
            throw new IllegalArgumentException("Grade with code does not exists");
        }
        gradeRepository.deleteById(gradeId);
    }
    @Transactional
    public void updateGrade(Long userId, String courseCode, int score) {
        User user = userRepository.findUserById(userId).orElseThrow(() -> new IllegalArgumentException("User with id " + userId + " does not exists"));
        Course course = courseRepository.findCourseByCode(courseCode).orElseThrow(() -> new IllegalArgumentException("Course with code " + courseCode + " does not exists"));

        Optional<Grade> grade = gradeRepository.findGradeByCourseAndStudent(course, user);
        if (grade.isPresent()){
            Grade existingGrade = grade.get();
            existingGrade.setScore(score);
        }else {
            Grade newGrade = new Grade(score, course, user);
            gradeRepository.save(newGrade);
        }

    }
}
