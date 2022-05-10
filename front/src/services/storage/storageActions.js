import {
    DELETE_STORAGE_BY_ID,
    FIND_ALL_STORAGES,
    FIND_STORAGE_BY_ID,
    SAVE_STORAGE,
    STORAGE_PROMISE
} from "./storageTypes";
import StorageService from "./StorageService";

export const findStorageById = (storageId) => {
    return async (dispatch) => {
        await dispatch({type: FIND_STORAGE_BY_ID});
        try {
            const res = await StorageService.findById(storageId);
            await dispatch(fetchStoragePromise(res.data));
            return Promise.resolve(res.data);
        } catch (err) {
            await dispatch(fetchStoragePromise(err));
            return Promise.reject(err);
        }
    };
}

export const findAllStorages = (pharmacyId, page = 1, size = 10) => {
    return async (dispatch) => {
        await dispatch({type: FIND_ALL_STORAGES});
        try {
            const res = await StorageService.findAll(pharmacyId, page, size);
            await dispatch(fetchStoragePromise(res.data));
            return Promise.resolve(res.data);
        } catch (err) {
            await dispatch(fetchStoragePromise(err));
            return Promise.reject(err);
        }
    };
}

export const saveStorage = (storage) => {
    return async (dispatch) => {
        await dispatch({type: SAVE_STORAGE});
        try {
            const res = await StorageService.save(storage);
            await dispatch(fetchStoragePromise(res.data));
            return Promise.resolve(res.data);
        } catch (err) {
            await dispatch(fetchStoragePromise(err));
            return Promise.reject(err);
        }
    };
}

export const deleteStorageById = (storageId) => {
    return async (dispatch) => {
        await dispatch({type: DELETE_STORAGE_BY_ID});
        try {
            const res = await StorageService.deleteById(storageId);
            await dispatch(fetchStoragePromise(res.data));
            return Promise.resolve(res.data);
        } catch (err) {
            await dispatch(fetchStoragePromise(err));
            return Promise.reject(err);
        }
    };
}


const fetchStoragePromise = (responseData) => {
    return {
        type: STORAGE_PROMISE, payload: responseData,
    };
};
