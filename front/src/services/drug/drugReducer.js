import {DELETE_DRUG_BY_ID, DRUG_PROMISE, FIND_ALL_DRUGS, FIND_DRUG_BY_ID, SAVE_DRUG, UPDATE_DRUG} from "./drugTypes";

const initialState = {
    drugPromise: null,
};

const drugReducer = (state = initialState, action) => {
    switch (action.type) {
        case FIND_DRUG_BY_ID || FIND_ALL_DRUGS
        || SAVE_DRUG || DELETE_DRUG_BY_ID || UPDATE_DRUG:
            return {
                ...state,
            };
        case DRUG_PROMISE: {
            return {
                drugPromise: action.payload,
            };
        }
        default:
            return state;
    }
};

export default drugReducer;
