package com.example.school.courses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    public List<Course> getCourses(int page, int size, String sortDir, String sort) {
        PageRequest pageReq
                = PageRequest.of(page, size, Sort.Direction.fromString(sortDir), sort);
        return courseRepository.findAll(pageReq).getContent();
    }

    public Course addNewCourse(Course course) {
        Optional<Course> optionalCourse = courseRepository.findCourseByCode(course.getCode());
        if (optionalCourse.isPresent()) {
            throw new IllegalArgumentException("Course Code already in use");
        }
        courseRepository.save(course);
        return course;
    }

    public void deleteCourse(String courseCode) {
        Optional<Course> optionalCourse = courseRepository.findCourseByCode(courseCode);
        if (optionalCourse.isEmpty()) {
            throw new IllegalArgumentException("Course with code does not exists");
        }
        courseRepository.delete(optionalCourse.get());
    }

    @Transactional
    public Course updateCourse(String courseCode, CourseDto courseDto) {
        String title = courseDto.getTitle();
        Integer units = courseDto.getUnits();
        Course course = courseRepository.findCourseByCode(courseCode).orElseThrow(() -> new IllegalArgumentException("Course with code " + courseCode + " does not exists"));
        if (title != null && title.length() != 0 && !Objects.equals(course.getTitle(), title)) {
            System.out.println(title);
            course.setTitle(title);
        }

        if (units != null && units != course.getUnits()) {

            course.setUnits(units);
        }
        return course;

    }

    public Course getCourseByCode(String courseCode) {
        return courseRepository.findCourseByCode(courseCode).orElseThrow(() -> new IllegalArgumentException("Course with code " + courseCode + " does not exists"));
    }
}
