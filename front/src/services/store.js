import rootReducer from "./rootReducer";
import {persistReducer} from "redux-persist";
import storage from "redux-persist/lib/storage";
import {configureStore} from "@reduxjs/toolkit";

const persistConfig = {
  key: "root", storage, whitelist: ["jwtToken"],
};

const persistedReducer = persistReducer(persistConfig, rootReducer);

const store = configureStore({
  reducer: persistedReducer, middleware: getDefaultMiddleware => getDefaultMiddleware({
    immutableCheck: false, serializableCheck: false, thunk: true,
  })
})

export default store;
