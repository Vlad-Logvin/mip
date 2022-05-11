import {
    DELETE_PHARMACIST_BY_ID,
    FIND_ALL_PHARMACISTS,
    FIND_PHARMACIST_BY_ID,
    SAVE_PHARMACIST,
    PHARMACIST_PROMISE
} from "./pharmacistTypes";
import PharmacistService from "./PharmacistService";

export const findPharmacistById = (pharmacistId) => {
    return async (dispatch) => {
        await dispatch({type: FIND_PHARMACIST_BY_ID});
        try {
            const res = await PharmacistService.findById(pharmacistId);
            await dispatch({
                type: PHARMACIST_PROMISE, payload: res.data,
            });
            return Promise.resolve(res.data);
        } catch (err) {
            await dispatch({
                type: PHARMACIST_PROMISE, payload: err,
            });
            return Promise.reject(err);
        }
    };
}

export const findAllPharmacists = (pharmacyId, page = 1, size = 10) => {
    return async (dispatch) => {
        await dispatch({type: FIND_ALL_PHARMACISTS});
        try {
            const res = await PharmacistService.findAll(pharmacyId, page, size);
            await dispatch({
                type: PHARMACIST_PROMISE, payload: res.data,
            });
            return Promise.resolve(res.data);
        } catch (err) {
            await dispatch({
                type: PHARMACIST_PROMISE, payload: err,
            });
            return Promise.reject(err);
        }
    };
}

export const savePharmacist = (pharmacist) => {
    return async (dispatch) => {
        await dispatch({type: SAVE_PHARMACIST});
        try {
            const res = await PharmacistService.save(pharmacist);
            await dispatch({
                type: PHARMACIST_PROMISE, payload: res.data,
            });
            return Promise.resolve(res.data);
        } catch (err) {
            await dispatch({
                type: PHARMACIST_PROMISE, payload: err,
            });
            return Promise.reject(err);
        }
    };
}

export const deletePharmacistById = (pharmacistId) => {
    return async (dispatch) => {
        await dispatch({type: DELETE_PHARMACIST_BY_ID});
        try {
            const res = await PharmacistService.deleteById(pharmacistId);
            await dispatch({
                type: PHARMACIST_PROMISE, payload: res.data,
            });
            return Promise.resolve(res.data);
        } catch (err) {
            await dispatch({
                type: PHARMACIST_PROMISE, payload: err,
            });
            return Promise.reject(err);
        }
    };
}





