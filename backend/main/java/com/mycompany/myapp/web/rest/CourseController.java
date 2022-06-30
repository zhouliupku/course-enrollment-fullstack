package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.security.SecurityUtils;
import com.mycompany.myapp.service.CourseService;
import com.mycompany.myapp.service.dto.CourseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class CourseController {

    private CourseService courseService;
    /**
     * 1. student enroll a course
     * 2. Http method: POST(create)
     * 3. URL: /student/course/{courseName}
     * 4. Http status code: 200, 201
     * 5. Request body: {courseName}
     * 6. Response body: None
     * 7. Request header: JWT token
     * http request -> URL + Http method -> Java method
     */

    @PostMapping(path="/student/course/{courseName}")
    @ResponseStatus(HttpStatus.CREATED)
    public void enrollCourse(@PathVariable String courseName) {
        String userName = getUserName();
        this.courseService.enrollCourse(userName, courseName);
    }

    /**
     * 1. list all courses
     * 2. Http method: GET(read)
     * 3. URL: /courses
     * 4. Http status code: 200
     * 5. Request body: None
     * 6. Response body: List<CourseDTO>
     * 7. Request header: JWT token
     * http request -> URL + Http method -> Java method
     */

    @GetMapping(path="/courses")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<CourseDTO> getAllCourses() {
        return courseService.getAllCourses();
    }

    /**
     * 1. list a student's all enrolled courses
     * 2. Http method: GET(read)
     * 3. URL: /student/courses
     * 4. Http status code: 200
     * 5. Request body: None(because student is in the token)
     * 6. Response body: List<CourseDTO>
     * 7. Request header: JWT token
     * http request -> URL + Http method -> Java method
     */
    @GetMapping(path="/student/courses")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<CourseDTO> getEnrolledCourses() {
        String userName = getUserName();
        return courseService.getEnrolledCourses(userName);
    }

    /**
     * 1. drop a course
     * 2. Http method: DELETE
     * 3. URL: /student/course/{courseName}
     * 4. Http status code: 200, 204
     * 5. Request body: courseName(PathVariable)
     * 6. Response body: List<CourseDTO>
     * 7. Request header: JWT token
     * http request -> URL + Http method -> Java method
     */
    @DeleteMapping(path="/student/course/{courseName}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void dropCourse(@PathVariable String courseName) {
        String userName = getUserName();
        courseService.dropCourse(userName, courseName);
    }

    /**
     * Extract username from JWT token
     * @return   username.
     */
    private String getUserName() {
        return SecurityUtils.getCurrentUserLogin().orElseThrow(() -> {
            throw new UsernameNotFoundException("Username not found!");
        });
    }


}
