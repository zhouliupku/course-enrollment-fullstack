package com.mycompany.myapp.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "user_course")
@Data
@NoArgsConstructor
public class UserCourse {

    // save new relationship
    public UserCourse(User user, Course course) {
        this.user = user;
        this.course = course;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne    // many: appear many times in user_course table, one: appear one time in user table
    private User user;     // foreign key, table relationship

    @JoinColumn(name = "course_id", referencedColumnName = "id")
    @ManyToOne
    private Course course;
}
