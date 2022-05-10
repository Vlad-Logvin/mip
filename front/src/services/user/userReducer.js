import {FIND_USER_BY_ID, FIND_USERS_BY_PHARMACY, LOGIN, LOGOUT, REGISTRATION, USER_PROMISE} from "./userTypes";

const initialState = {
  userPromise: null,
};

const userReducer = (state = initialState, action) => {
  switch (action.type) {
    case FIND_USER_BY_ID || LOGOUT
    || REGISTRATION || LOGIN || FIND_USERS_BY_PHARMACY:
      return {
        ...state,
      };
    case USER_PROMISE: {
      return {
        userPromise: action.payload,
      };
    }
    default:
      return state;
  }
};

export default userReducer;
