import axios from "axios";


const DRUG_BASE_URL = "http://localhost:8080/drugs";

class DrugService {
    findById = async (drugId) => {
        return axios.get(`${DRUG_BASE_URL}/${drugId}`);
    }

    findAll = async (pharmacyId, name, page = 1, size = 10) => {
        return axios.get(`http://localhost:8080/pharmacies/${pharmacyId}/drugs?page=${page}&size=${size}` + (name ? `&name=${name}` : ""));
    }

    deleteById = async (drugId) => {
        return axios.delete(`${DRUG_BASE_URL}/${drugId}`);
    }

    save = async (drug) => {
        return axios.post(`${DRUG_BASE_URL}`, drug);
    }

    update = async (drugId, drug) => {
        return axios.put(`${DRUG_BASE_URL}/${drugId}`, drug);
    }
}

export default new DrugService();
