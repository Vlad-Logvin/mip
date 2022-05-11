import axios from "axios";


const PHARMACIST_BASE_URL = "http://localhost:8080/pharmacists";

class PharmacistService {
    findById = async (pharmacistId) => {
        return axios.get(`${PHARMACIST_BASE_URL}/${pharmacistId}`);
    }

    findAll = async (pharmacyId, page = 1, size = 10) => {
        return axios.get(`http://localhost:8080/pharmacies/${pharmacyId}/pharmacists?page=${page}&size=${size}`);
    }

    deleteById = async (pharmacistId) => {
        return axios.delete(`${PHARMACIST_BASE_URL}/${pharmacistId}`);
    }

    save = async (pharmacist) => {
        return axios.post(`${PHARMACIST_BASE_URL}`, pharmacist);
    }
}

export default new PharmacistService();
