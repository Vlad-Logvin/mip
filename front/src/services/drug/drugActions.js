import {DELETE_DRUG_BY_ID, DRUG_PROMISE, FIND_ALL_DRUGS, FIND_DRUG_BY_ID, SAVE_DRUG, UPDATE_DRUG} from "./drugTypes";
import DrugService from "./DrugService";

export const findDrugById = (drugId) => {
    return async (dispatch) => {
        await dispatch({type: FIND_DRUG_BY_ID});
        try {
            const res = await DrugService.findById(drugId);
            await dispatch({
                type: DRUG_PROMISE, payload: res.data,
            });
            return Promise.resolve(res.data);
        } catch (err) {
            await dispatch({
                type: DRUG_PROMISE, payload: err,
            });
            return Promise.reject(err);
        }
    };
}

export const findAllDrugs = (pharmacyId, name, page = 1, size = 10) => {
    return async (dispatch) => {
        await dispatch({type: FIND_ALL_DRUGS});
        try {
            const res = await DrugService.findAll(pharmacyId, name, page, size);
            await dispatch({
                type: DRUG_PROMISE, payload: res.data,
            });
            return Promise.resolve(res.data);
        } catch (err) {
            await dispatch({
                type: DRUG_PROMISE, payload: err,
            });
            return Promise.reject(err);
        }
    };
}

export const saveDrug = (drug) => {
    return async (dispatch) => {
        await dispatch({type: SAVE_DRUG});
        try {
            const res = await DrugService.save(drug);
            await dispatch({
                type: DRUG_PROMISE, payload: res.data,
            });
            return Promise.resolve(res.data);
        } catch (err) {
            await dispatch({
                type: DRUG_PROMISE, payload: err,
            });
            return Promise.reject(err);
        }
    };
}

export const updateDrug = (drugId, drug) => {
    return async (dispatch) => {
        await dispatch({type: UPDATE_DRUG});
        try {
            const res = await DrugService.update(drugId, drug);
            await dispatch({
                type: DRUG_PROMISE, payload: res.data,
            });
            return Promise.resolve(res.data);
        } catch (err) {
            await dispatch({
                type: DRUG_PROMISE, payload: err,
            });
            return Promise.reject(err);
        }
    };
}

export const deleteDrugById = (drugId) => {
    return async (dispatch) => {
        await dispatch({type: DELETE_DRUG_BY_ID});
        try {
            const res = await DrugService.deleteById(drugId);
            await dispatch({
                type: DRUG_PROMISE, payload: res.data,
            });
            return Promise.resolve(res.data);
        } catch (err) {
            await dispatch({
                type: DRUG_PROMISE, payload: err,
            });
            return Promise.reject(err);
        }
    };
}





