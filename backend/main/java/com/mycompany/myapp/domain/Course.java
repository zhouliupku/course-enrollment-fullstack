package com.mycompany.myapp.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "course")
@Data   // getter, setter
public class Course {
    @Id  // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // auto increment
    @Column(name = "id")
    private Long id;

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "course_location")
    private String courseLocation;

    @Column(name = "course_content")
    private String courseContent;

    @Column(name = "teacher_id")
    private Long teacherId;
}
