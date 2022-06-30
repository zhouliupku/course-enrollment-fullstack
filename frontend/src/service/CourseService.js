import axios from "../axios/config"

export const CourseService = {
    getAllCourses: function() {
        return axios.get('/api/courses')
        
    },

    enrollCourse: function(courseName) {
        return axios.post(`/api/student/course/${courseName}`)
    },

    getEnrolledCourses: function() {
        return axios.get('/api/student/courses')
    },

    dropCourse: function(courseName) {
        return axios.delete(`/api/student/course/${courseName}`)
    }
}