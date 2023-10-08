package com.example.school.grades;

import com.example.school.users.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/v1/grade")
public class GradeController {
    private final GradeService gradeService;
    private final ModelMapper modelMapper;

    @Autowired
    public GradeController(GradeService gradeService, ModelMapper modelMapper) {
        this.gradeService = gradeService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/")
    public List<Grade> getGrades (
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "100") int size,
            @RequestParam(required = false, defaultValue = "ASC") String sort_dir,
            @RequestParam(required = false, defaultValue = "score") String sort){
        page--; // Pages are zero indexed in the Repository
        return this.gradeService.getGrades(page, size, sort_dir, sort);
    }
    @PostMapping
    public Grade registerGrade(@RequestBody GradeDto gradeDto){
        return gradeService.addNewGrade(gradeDto);

    }
    @DeleteMapping(path = "{gradeId}")
    public void deleteGrade(@PathVariable("gradeId") Long gradeId){
        this.gradeService.deleteGrade(gradeId);
    }
    private GradeDto convertToDto(Grade grade) {
        GradeDto gradeDto = modelMapper.map(grade, GradeDto.class);
        gradeDto.setUser(grade.getUser().getId());
        gradeDto.setCourse(grade.getCourse().getCode());
        return gradeDto;
    }
    private List<GradeDto> getUserGrades(User user) {
        List<Grade> gradeGrades = gradeService.getUserGrades(user);
        return gradeGrades.stream().map(grade -> modelMapper.map(grade, GradeDto.class)).collect(Collectors.toList());
    }
}

