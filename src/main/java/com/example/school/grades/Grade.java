package com.example.school.grades;

import com.example.school.users.User;
import com.example.school.courses.Course;

import javax.persistence.*;

import static com.example.school.SchoolConstants.SCHOOL_SEQUENCE_NAME;

@Entity
@Table(name="course_grade", schema = "public")
public class Grade {
    @Id
    @SequenceGenerator(name=SCHOOL_SEQUENCE_NAME, sequenceName =SCHOOL_SEQUENCE_NAME, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SCHOOL_SEQUENCE_NAME)
    private Long id;
    private int score;
    @Transient
    private int point;
    @Transient
    private int totalCreditPoint;
    @Transient
    private Character label;
    @ManyToOne
    @JoinColumn(name="course_id")
    private Course course;
    @ManyToOne
    @JoinColumn(name="student_id")
    private User student;

    public Grade(Integer score, Course course, User student) {
        this.score = score;
        this.course = course;
        this.student = student;
    }

    public Grade() {

    }

    @Override
    public String toString() {
        return "Grade{" +
                "id=" + id +
                ", student='" + student.getName() + '\'' +
                ", course='" + course.getTitle() + '\'' +
                ", label=" + label +
                '}';
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getPoint() {
        if (score <= 100 && score >=70){
            return 5;
        }else if (score < 70 && score >= 60){
            return 4;
        }else if (score < 60 && score >= 50){
            return 3;
        }else if (score < 50 && score >= 45){
            return 2;
        }else if (score < 45 && score >= 40){
            return 1;
        }
        return 0;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Character getLabel() {
        switch (point){
            case 5: return 'A';
            case 4: return 'B';
            case 3: return 'C';
            case 2: return 'D';
            case 1: return 'E';
        }
        return 'F';
    }

    public void setLabel(Character label) {
        this.label = label;
    }

    public void setTotalCreditPoint(Integer totalCreditPoint) {
        this.totalCreditPoint = totalCreditPoint;
    }

    public int getTotalCreditPoint() {
        return this.getPoint() * course.getUnits();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}