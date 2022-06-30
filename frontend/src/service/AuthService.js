import axios from "../axios/config"

export const AuthService = {
    getJWTToken: function (username, password) {
        return axios.post("/api/authenticate", {
            "username": username,
            "password": password
        })
    }
}