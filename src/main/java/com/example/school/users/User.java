package com.example.school.users;

import javax.persistence.*;

import java.time.LocalDate;
import java.time.Period;

import static com.example.school.SchoolConstants.SCHOOL_SEQUENCE_NAME;

@Entity
@Table(name="user", schema = "public")
public class User {
    @Id
    @SequenceGenerator(name=SCHOOL_SEQUENCE_NAME, sequenceName =SCHOOL_SEQUENCE_NAME, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SCHOOL_SEQUENCE_NAME)
    private Long id;
    private String name;
    private String email;
    private LocalDate dob;
    @Transient
    private Integer age;

    User(Long id, String name, String email, LocalDate dob) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dob = dob;
    }

    public User() {

    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", dob=" + dob + '\'' +
                ", age=" + this.getAge() +
                '}';
    }

    public int getAge() {
        return Period.between(this.dob, LocalDate.now()).getYears();
    }

    public void setAge(int age) {
        this.age = age;
    }
}