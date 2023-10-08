package com.example.school.users.tests;

import com.example.school.users.User;
import com.example.school.users.UserDto;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserDtoUnitTest {

    private ModelMapper modelMapper = new ModelMapper();

    @Test
    public void whenConvertUserEntityToUserDto_thenCorrect() {
        User user = new User();
        user.setId(1L);
        user.setName("John Doe");
        user.setDob(LocalDate.of(1999, 9, 9));

        UserDto userDto = modelMapper.map(user, UserDto.class);
        assertEquals(user.getId(), userDto.getId());
        assertEquals(user.getName(), userDto.getName());
        assertEquals(user.getDob(), userDto.getDob());
    }

    @Test
    public void whenConvertUserDtoToUserEntity_thenCorrect() {
        UserDto userDto = new UserDto();
        userDto.setId(1L);
        userDto.setName("John Doe");
        userDto.setDob(LocalDate.of(1999, 1, 1));

        User user = modelMapper.map(userDto, User.class);
        assertEquals(userDto.getId(), user.getId());
        assertEquals(userDto.getName(), user.getName());
        assertEquals(userDto.getDob(), user.getDob());
    }
}