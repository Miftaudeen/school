package com.example.school.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
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

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public void addNewUser(User user) {
        Optional<User> optionalUser = userRepository.findUserByEmail(user.getEmail());
        if (optionalUser.isPresent()){
            throw new IllegalArgumentException("User Email already in use");
        }
        userRepository.save(user);
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
        if (name != null && name.length() != 0 && !Objects.equals(user.getname(), name)){
            user.setname(name);
        }

        if (email != null  && !Objects.equals(email, user.getEmail())){
            Optional<User> userOptional = userRepository.findUserByEmail(email);
            if (userOptional.isPresent()){
                throw new IllegalStateException("User with this email already exists");
            }
            user.setEmail(email);
        }

    }
}
