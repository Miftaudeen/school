package com.example.school.courses;

import lombok.NoArgsConstructor;

import javax.persistence.*;

import static com.example.school.courses.Constants.COURSE_SEQUENCE_NAME;

@Entity
@NoArgsConstructor
@Table(name="course", schema = "public")
public class Course{
    @Id
    @SequenceGenerator(name=COURSE_SEQUENCE_NAME, sequenceName =COURSE_SEQUENCE_NAME, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = COURSE_SEQUENCE_NAME)
    private Long id;
    private String title;
    @Column(unique=true)
    private String code;
    private int units;

    public Course(String title, String code, int units) {
        this.title = title;
        this.code = code;
        this.units = units;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", code='" + code + '\'' +
                ", units=" + units +
                '}';
    }
}