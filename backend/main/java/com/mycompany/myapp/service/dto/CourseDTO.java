package com.mycompany.myapp.service.dto;

import lombok.Builder;
import lombok.Data;

@Builder  // Design pattern, use when the parameters of constructor >= 4, can ignore order of parameters.
@Data
public class CourseDTO {
    private String courseName;
    private String courseContent;
    private String courseLocation;
    private long teacherId;

}
