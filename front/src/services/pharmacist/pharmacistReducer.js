import {FIND_PHARMACIST_BY_ID, FIND_ALL_PHARMACISTS, SAVE_PHARMACIST, DELETE_PHARMACIST_BY_ID, PHARMACIST_PROMISE} from "./pharmacistTypes";

const initialState = {
    pharmacistPromise: null,
};

const pharmacistReducer = (state = initialState, action) => {
    switch (action.type) {
        case FIND_PHARMACIST_BY_ID || FIND_ALL_PHARMACISTS
        || SAVE_PHARMACIST || DELETE_PHARMACIST_BY_ID:
            return {
                ...state,
            };
        case PHARMACIST_PROMISE: {
            return {
                pharmacistPromise: action.payload,
            };
        }
        default:
            return state;
    }
};

export default pharmacistReducer;
