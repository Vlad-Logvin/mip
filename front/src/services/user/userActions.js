import {FIND_USER_BY_ID, FIND_USERS_BY_PHARMACY, LOGIN, LOGOUT, REGISTRATION, USER_PROMISE} from "./userTypes";

import UserService from "./UserService";
import localStorage from "redux-persist/es/storage";

export const findUsersByPharmacy = (pharmacyId, currentPage, userPerPage) => {
  return async (dispatch) => {
    dispatch({type: FIND_USERS_BY_PHARMACY});
    try {
      const res = await UserService.findUsersByPharmacy(pharmacyId, currentPage, userPerPage);
      dispatch(fetchUserPromise(res.data));
      return Promise.resolve(res.data);
    } catch (err) {
      dispatch(fetchUserPromise(err));
      return Promise.reject(err);
    }
  };
};

export const findUserById = (userId) => {
  return async (dispatch) => {
    dispatch({type: FIND_USER_BY_ID});
    try {
      const res = await UserService.findUserById(userId);
      dispatch(fetchUserPromise(res.data));
      return Promise.resolve(res.data);
    } catch (err) {
      dispatch(fetchUserPromise(err));
      return Promise.reject(err);
    }
  };
};

export const login = (email, password) => {
  return async (dispatch) => {
    dispatch({type: LOGIN});
    try {
      const res = await UserService.login(email, password);
      await setLocalStorage(res.data);
      await dispatch(fetchUserPromise(res.data));
    } catch (err) {
      await dispatch(fetchUserPromise(err));
    }
  };
};

export const logoutUser = () => {
  return async (dispatch) => {
    dispatch({type: LOGOUT});
    removeLocalStorage();
    dispatch(fetchUserPromise(null));
  };
};

export const registerUser = (email, password, username) => {
  return async (dispatch) => {
    dispatch({type: REGISTRATION});
    try {
      const res = await UserService.registerUser(email, password, username);
      setLocalStorage(res.data);
      dispatch(fetchUserPromise(res.data));
    } catch (err) {
      dispatch(fetchUserPromise(err));
    }
  };
};

const setLocalStorage = (responseData) => {
  localStorage.setItem("jwtToken", responseData.token);
  localStorage.setItem("pharmacyId", responseData.pharmacy)
  localStorage.setItem("isLoggedIn", responseData.loggedIn);
  localStorage.setItem("username", responseData.username);
  localStorage.setItem("role", responseData.roles[0].role);
  localStorage.setItem("id", responseData.id);
};

const removeLocalStorage = () => {
  localStorage.removeItem("jwtToken");
  localStorage.removeItem("pharmacyId");
  localStorage.removeItem("isLoggedIn");
  localStorage.removeItem("username");
  localStorage.removeItem("role");
  localStorage.removeItem("id");
};

const fetchUserPromise = (responseData) => {
  return {
    type: USER_PROMISE, payload: responseData,
  };
};
