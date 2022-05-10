import axios from "axios";

const USER_BASE_URL = "http://localhost:8080/users";

class UserService {
    login = async (email, password) => {
        return axios.post(USER_BASE_URL + "/login", {
            email: email, password: password,
        });
    };

    registerUser = async (email, password, name, surname) => {
        return axios.post(USER_BASE_URL, {
            email: email, password: password, name: name, surname: surname,
        });
    };

    deleteUserById = async (userId) => {
        return axios.delete(USER_BASE_URL + "/" + userId);
    }

    findUsersByPharmacy = async (pharmacyId, page = 1, size = 10) => {
        return axios.get(`http://localhost:8080/pharmacies/${pharmacyId}/users?page=${page}&size=${size}`);
    }

    findUserById = async (userId) => {
        return axios.get(USER_BASE_URL + "/" + userId);
    };
}

export default new UserService();
