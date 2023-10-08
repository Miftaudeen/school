package com.example.school.grades;

import com.example.school.courses.CourseService;
import com.example.school.users.User;
import com.example.school.courses.Course;
import com.example.school.users.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GradeService {
    private final GradeRepository gradeRepository;
    private final UserService userService;
    private final CourseService courseService;

    public List<Grade> getGrades(int page, int size, String sortDir, String sort) {
        PageRequest pageReq
                = PageRequest.of(page, size, Sort.Direction.fromString(sortDir), sort);
        return gradeRepository.findAll(pageReq).getContent();
    }

    public Grade addNewGrade(GradeDto gradeDto) {
        User user = userService.getUserById(gradeDto.getUser());
        Course course = courseService.getCourseByCode(gradeDto.getCourse());
        Optional<Grade> grade = gradeRepository.findGradeByCourseAndUser(course, user);
        if (grade.isPresent()){
            Grade existingGrade = grade.get();
            existingGrade.setScore(gradeDto.getScore());
            return existingGrade;
        }else {
            Grade newGrade = new Grade(gradeDto.getScore(), course, user);
            gradeRepository.save(newGrade);
            return newGrade;
        }
    }

    public void deleteGrade(Long gradeId) {
        boolean exists = gradeRepository.existsById(gradeId);
        if (!exists){
            throw new IllegalArgumentException("Grade with code does not exists");
        }
        gradeRepository.deleteById(gradeId);
    }

    public List<Grade> getUserGrades(User user) {
        return gradeRepository.findGradeByUser(user);
    }
}
