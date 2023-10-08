package com.example.school.courses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/course")
public class CourseController {
    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/")
    public List<Course> getCourses (
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "100") int size,
            @RequestParam(required = false, defaultValue = "ASC") String sort_dir,
            @RequestParam(required = false, defaultValue = "title") String sort){
        page--; // Pages are zero indexed in the Repository
        return this.courseService.getCourses(page, size, sort_dir, sort);
    }
    @PostMapping
    public Course registerCourse(@RequestBody Course course){
         return this.courseService.addNewCourse(course);
    }
    @DeleteMapping(path = "{courseCode}")
    public void deleteCourse(@PathVariable("courseCode") String courseCode){
        this.courseService.deleteCourse(courseCode);
    }
    @PutMapping(path ="{courseCode}")
    public Course updateCourse(@PathVariable("courseCode") String courseCode,
                             @RequestBody(required = false) CourseDto courseDto ){
        return this.courseService.updateCourse(courseCode, courseDto);
    }
}
