package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course/*ORM model class*/, Long/*Primary key type*/> {

    Optional<Course> findOneByCourseName(String courseName);  // implemented by hibernate
}
