import React from 'react';
import ReactDOM from 'react-dom/client';
import store from "./services/store";
import {Provider} from "react-redux";
import {PersistGate} from "redux-persist/integration/react";
import {persistStore} from "redux-persist";
import {BrowserRouter, Navigate, Route, Routes} from "react-router-dom";
import NotFound from "./components/NotFound";
import NotEnoughPermission from "./components/NotEnoughPermission";
import Login from "./components/Login";
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import Home from "./components/Home";
import Register from "./components/Register";
import Storages from "./components/Storages";
import Receipts from "./components/Receipts";
import Reports from "./components/Reports";
import Drugs from "./components/Drugs";
import Pharmacists from "./components/Pharmacists";
import Pharmacies from "./components/Pharmacies";

const persistedStore = persistStore(store);
const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(<React.StrictMode>
    <Provider store={store}>
        <PersistGate loading={null} persistor={persistedStore}>
            <BrowserRouter>
                <Routes>
                    <Route path="login" element={<Login/>}/>
                    <Route path="register" element={<Register/>}/>
                    <Route path="" element={<Navigate replace to="/home"/>}/>
                    <Route path="home"
                           element={localStorage.isLoggedIn === 'true' ? <Home/> : <Navigate replace to="/login"/>}/>
                    <Route path="storages"
                           element={localStorage.isLoggedIn === 'true' ? (localStorage.role !== "ROLE_ADMIN" ?
                               <Storages/> : <NotEnoughPermission/>) : <Navigate replace to="/login"/>}/>
                    <Route path="receipts"
                           element={localStorage.isLoggedIn === 'true' ? (localStorage.role !== "ROLE_ADMIN" ?
                               <Receipts/> : <NotEnoughPermission/>) : <Navigate replace to="/login"/>}/>
                    <Route path="reports"
                           element={localStorage.isLoggedIn === 'true' ? (localStorage.role !== "ROLE_ADMIN" ?
                               <Reports/> : <NotEnoughPermission/>) : <Navigate replace to="/login"/>}/>
                    <Route path="drugs"
                           element={localStorage.isLoggedIn === 'true' ? (localStorage.role !== "ROLE_ADMIN" ?
                               <Drugs/> : <NotEnoughPermission/>) : <Navigate replace to="/login"/>}/>
                    <Route path="pharmacists"
                           element={localStorage.isLoggedIn === 'true' ? (localStorage.role === "ROLE_PHARMACY_OWNER" ?
                               <Pharmacists/> : <NotEnoughPermission/>) : <Navigate replace to="/login"/>}/>
                    <Route path="pharmacies" element={localStorage.isLoggedIn === 'true' ? <Pharmacies/> :
                        <Navigate replace to="/login"/>}/>
                    <Route path="*" element={<NotFound/>}/>
                </Routes>
            </BrowserRouter>
        </PersistGate>
    </Provider>
</React.StrictMode>);
