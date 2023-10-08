package com.example.school.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers(int page, int size, String sortDir, String sort) {
        PageRequest pageReq
                = PageRequest.of(page, size, Sort.Direction.fromString(sortDir), sort);
        Page<User> users = userRepository.findAll(pageReq);
        return users.getContent();
    }

    public User addNewUser(User user) {
        Optional<User> optionalUser = userRepository.findUserByEmail(user.getEmail());
        if (optionalUser.isPresent()){
            throw new IllegalArgumentException("User Email already in use");
        }
        userRepository.save(user);
        return user;
    }

    public void deleteUser(Long userId) {
        boolean exists = userRepository.existsById(userId);
        if (!exists){
            throw new IllegalArgumentException("User with id does not exists");
        }
        userRepository.deleteById(userId);
    }
    @Transactional
    public void updateUser(Long userId, String name, String email) {
        User user = userRepository.findUserById(userId).orElseThrow(() -> new IllegalArgumentException("User with Id " + userId + " does not exists"));
        if (name != null && name.length() != 0 && !Objects.equals(user.getName(), name)){
            user.setName(name);
        }

        if (email != null  && !Objects.equals(email, user.getEmail())){
            Optional<User> userOptional = userRepository.findUserByEmail(email);
            if (userOptional.isPresent()){
                throw new IllegalStateException("User with this email already exists");
            }
            user.setEmail(email);
        }

    }

    public User getUserById(Long userId) {
        return userRepository.findUserById(userId).orElseThrow(() -> new IllegalArgumentException("User with Id " + userId + " does not exists"));
    }
}
