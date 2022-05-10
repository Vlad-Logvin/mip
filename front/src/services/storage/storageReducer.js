import {FIND_STORAGE_BY_ID, FIND_ALL_STORAGES, SAVE_STORAGE, DELETE_STORAGE_BY_ID, STORAGE_PROMISE} from "./storageTypes";

const initialState = {
    storagePromise: null,
};

const storageReducer = (state = initialState, action) => {
    switch (action.type) {
        case FIND_STORAGE_BY_ID || FIND_ALL_STORAGES
        || SAVE_STORAGE || DELETE_STORAGE_BY_ID:
            return {
                ...state,
            };
        case STORAGE_PROMISE: {
            return {
                storagePromise: action.payload,
            };
        }
        default:
            return state;
    }
};

export default storageReducer;
