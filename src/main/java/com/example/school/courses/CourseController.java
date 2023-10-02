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
    public List<Course> getCourses (){
        return this.courseService.getCourses();
    }
    @PostMapping
    public void registerCourse(@RequestBody Course course){
        this.courseService.addNewCourse(course);
    }
    @DeleteMapping(path = "{courseCode}")
    public void deleteCourse(@PathVariable("courseCode") String courseCode){
        this.courseService.deleteCourse(courseCode);
    }
    @PutMapping(path ="{courseCode}")
    public void updateCourse(@PathVariable("courseCode") String courseCode,
                             @RequestParam(required = false) String title,
                             @RequestParam(required = false) Integer units ){
        this.courseService.updateCourse(courseCode,title, units);
    }
}
