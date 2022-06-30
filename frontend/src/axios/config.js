import { TOKEN_COOKIE_NAME } from "../Constant"
import cookie from 'react-cookies'
import axios from 'axios'


const token = cookie.load(TOKEN_COOKIE_NAME)

export default axios.create(
    {
        baseURL:"http://course-enroll-alb-741567681.us-west-2.elb.amazonaws.com:8080",
        headers:{
            Authorization: `Bearer ${token}`
        }
    } 
)