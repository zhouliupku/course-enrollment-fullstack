import React from "react";
import { CourseService } from "../service/CourseService";
import CourseTable from "../components/CourseTable";

export default class EnrolledCourses extends React.Component {

    state = {
        courses: []
    }

    componentDidMount() {
        CourseService.getEnrolledCourses().then(response => {
            this.setState({
                courses: response.data
            })
        }).catch(error => {
            console.log(error)
        })
    }

    dropCourse(courseName) {
        CourseService.dropCourse(courseName)
        .then(response => {
            alert(`${courseName} has been dropped successfully!`)
            window.location.reload();
        }).catch(error => {
            alert(`Drop failed due to ${error}!`)
        })
    }

    render() {
        return (
            <div>
                <CourseTable courses={this.state.courses} buttonText={"Drop"} buttonColor={"error"} handleButtonClick={this.dropCourse}/>
            </div>
        )
    }
}


// use state & use effect -react hook
