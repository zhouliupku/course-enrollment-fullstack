package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Course;
import com.mycompany.myapp.domain.User;
import com.mycompany.myapp.domain.UserCourse;
import com.mycompany.myapp.repository.CourseRepository;
import com.mycompany.myapp.repository.UserCourseRepository;
import com.mycompany.myapp.repository.UserRepository;
import com.mycompany.myapp.service.dto.CourseDTO;
import com.mycompany.myapp.service.mapper.CourseMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CourseService {

    private UserRepository userRepository;
    private CourseRepository courseRepository;
    private UserCourseRepository userCourseRepository;
    private CourseMapper courseMapper;
    public void enrollCourse(String userName, String courseName) {
        // Check user and course exist
        UserCourse userCourse = getUserCourse(userName, courseName);
        // Check if UserCourse not exist -> don't want it exists
        Optional<UserCourse> optionalUserCourse = userCourseRepository.findOneByUserAndCourse(userCourse.getUser(),
            userCourse.getCourse());
        optionalUserCourse.ifPresent(existingUserCourse -> {
            throw new IllegalArgumentException("UserCourse already exists: " + existingUserCourse.toString());
        });
        // save new userCourse into user course table
        userCourseRepository.save(userCourse);
    }

    /**
     * 1. Check if user exists
     * 2. Check if course exists
     * Return a new UserCourse
     */
    private UserCourse getUserCourse(String userName, String courseName) {
        // check user: check user table by user repository
        Optional<User> optionalUser = userRepository.findOneByLogin(userName);
        // Optional: stress check on null pointer exception
        User user = optionalUser.orElseThrow(() -> new UsernameNotFoundException("No such user: " + userName));

        // check course
        Optional<Course> optionalCourse = courseRepository.findOneByCourseName(courseName);
        Course course = optionalCourse.orElseThrow(() -> new IllegalArgumentException("No such course: " + courseName));
        return new UserCourse(user, course);


    }

    public List<CourseDTO> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        // course -> courseDTO: mapper
        return courses.stream().map(course -> courseMapper.convert(course)).collect(Collectors.toList());
    }

    public List<CourseDTO> getEnrolledCourses(String userName) {
        // check user, get user
        Optional<User> optionalUser = userRepository.findOneByLogin(userName);
        User user = optionalUser.orElseThrow(() -> new UsernameNotFoundException("No such user: " + userName));
        // find courses by user
        List<UserCourse> userCourseList = userCourseRepository.findAllByUser(user);
        return userCourseList.stream()
            .map(userCourse -> userCourse.getCourse())
            .map(course -> courseMapper.convert(course))
            .collect(Collectors.toList());
    }

    public void dropCourse(String userName, String courseName) {
        // check user, course exist
        UserCourse userCourse = getUserCourse(userName, courseName);  // doesn't include id, thus cannot use ....delete(entity);
        // Delete userCourse
        userCourseRepository.deleteByUserAndCourse(userCourse.getUser(), userCourse.getCourse());
    }
}
