import {combineReducers} from "redux";
import userReducer from "./user/userReducer";
import storageReducer from "./storage/storageReducer";
import drugReducer from "./drug/drugReducer";

const rootReducer = combineReducers({user: userReducer, storage: storageReducer, drug: drugReducer});

export default rootReducer;
