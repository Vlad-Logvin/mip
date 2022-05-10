import axios from "axios";


const STORAGE_BASE_URL = "http://localhost:8080/storages";

class StorageService {
    findById = async (storageId) => {
        return axios.get(`${STORAGE_BASE_URL}/${storageId}`);
    }

    findAll = async (pharmacyId, page = 1, size = 10) => {
        return axios.get(`http://localhost:8080/pharmacies/${pharmacyId}/storages?page=${page}&size=${size}`);
    }

    deleteById = async (storageId) => {
        return axios.delete(`${STORAGE_BASE_URL}/${storageId}`);
    }

    save = async (storage) => {
        return axios.post(`${STORAGE_BASE_URL}`, storage);
    }
}

export default new StorageService();
