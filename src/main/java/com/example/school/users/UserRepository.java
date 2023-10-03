package com.example.school.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserById(Long id);

    boolean existsById(Long id);

    void deleteById(Long id);
    @Query("SELECT s from User s where s.email = ?1")
    Optional<User> findUserByEmail(String email);
}
