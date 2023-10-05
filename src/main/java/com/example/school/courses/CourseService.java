package com.example.school.courses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getCourses() {
        return courseRepository.findAll();
    }

    public void addNewCourse(Course course) {
        Optional<Course> optionalCourse = courseRepository.findCourseByCode(course.getCode());
        if (optionalCourse.isPresent()){
            throw new IllegalArgumentException("Course Code already in use");
        }
        courseRepository.save(course);
    }

    public void deleteCourse(String courseCode) {
        boolean exists = courseRepository.existsByCode(courseCode);
        if (!exists){
            throw new IllegalArgumentException("Course with code does not exists");
        }
        courseRepository.deleteByCode(courseCode);
    }
    @Transactional
    public void updateCourse(String courseCode, String title, int units) {
        Course course = courseRepository.findCourseByCode(courseCode).orElseThrow(() -> new IllegalArgumentException("Course with code " + courseCode + " does not exists"));
        System.out.println(title);
        if (title != null && title.length() != 0 && !Objects.equals(course.getTitle(), title)){
            System.out.println(title);
            course.setTitle(title);
        }

        if ( units != course.getUnits()){
            course.setUnits(units);
        }

    }
}
