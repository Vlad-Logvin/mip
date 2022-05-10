import {combineReducers} from "redux";
import userReducer from "./user/userReducer";
import storageReducer from "./storage/storageReducer";

const rootReducer = combineReducers({user: userReducer, storage: storageReducer});

export default rootReducer;
