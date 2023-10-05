package com.example.school.users;

import javax.persistence.*;

import java.time.LocalDate;
import java.time.Period;

import static com.example.school.SchoolConstants.SCHOOL_SEQUENCE_NAME;

@Entity
@Table(name="user", schema = "public")
public class User {
    public Long getId() {
        return id;
    }

    @Id
    @SequenceGenerator(name=SCHOOL_SEQUENCE_NAME, sequenceName =SCHOOL_SEQUENCE_NAME, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SCHOOL_SEQUENCE_NAME)
    private Long id;
    private String name;
    @Column(unique=true)
    private String email;
    private LocalDate dob;
    @Transient
    private Integer age;

    public User(String name, String email, LocalDate dob) {
        this.name = name;
        this.email = email;
        this.dob = dob;
    }

    public User() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
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
                "id=" + this.id +
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

    public void setId(Long id) {
        this.id = id;
    }
}