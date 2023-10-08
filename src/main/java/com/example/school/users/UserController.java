package com.example.school.users;

import com.example.school.grades.Grade;
import com.example.school.grades.GradeDto;
import com.example.school.grades.GradeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {
    private final UserService userService;
    @Autowired
    private final GradeService gradeService;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public UserController(UserService userService, GradeService gradeService) {
        this.userService = userService;
        this.gradeService = gradeService;
    }

    @GetMapping("/")
    public List<UserDto> getUsers(
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "100") int size,
            @RequestParam(required = false, defaultValue = "ASC") String sort_dir,
            @RequestParam(required = false, defaultValue = "name") String sort
                               ) {
        page--; // Pages are zero indexed in the Repository
        List<User> users = this.userService.getUsers(page, size, sort_dir, sort);
        return users.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public UserDto registerUser(@RequestBody UserDto userDto) {
        User user = convertToEntity(userDto);
        User userRegistered = this.userService.addNewUser(user);
        return convertToDto(userRegistered);
    }

    @DeleteMapping(path = "{userId}")
    public void deleteUser(@PathVariable("userId") Long userId) {
        this.userService.deleteUser(userId);
    }

    @PutMapping(path = "{userId}")
    public void updateUser(@PathVariable("userId") Long userId,
                           @RequestParam(required = false) String name,
                           @RequestParam(required = false) String email) {
        this.userService.updateUser(userId, name, email);
    }

    private UserDto convertToDto(User user) {
        UserDto userDto = modelMapper.map(user, UserDto.class);
        userDto.setDob(user.getDob());
        return userDto;
    }
    private List<GradeDto> getUserGrades(User user) {
        List<Grade> userGrades = gradeService.getUserGrades(user);
        List<GradeDto> gradeDtos = userGrades.stream().map(grade -> modelMapper.map(grade, GradeDto.class)).collect(Collectors.toList());
        return gradeDtos;
    }
    private User convertToEntity(UserDto userDto){
        User user = modelMapper.map(userDto, User.class);
        user.setDob(userDto.getDob());
        return user;
    }
}
