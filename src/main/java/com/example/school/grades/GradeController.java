package com.example.school.grades;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/grade")
public class GradeController {
    private final GradeService gradeService;

    @Autowired
    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @GetMapping("/")
    public List<Grade> getCourses (){
        return this.gradeService.getGrades();
    }
    @PostMapping
    public void registerCourse(@RequestBody Grade grade){
        this.gradeService.addNewGrade(grade);
    }
    @DeleteMapping(path = "{courseCode}")
    public void deleteCourse(@PathVariable("gradeId") Long gradeId){
        this.gradeService.deleteGrade(gradeId);
    }
    @PutMapping(path ="{courseCode}")
    public void updateCourse(@PathVariable("courseCode") String courseCode,
                             @RequestParam(required = false) Long userId,
                             @RequestParam(required = false) Integer score ){
        this.gradeService.updateGrade(userId, courseCode, score);
    }
}
