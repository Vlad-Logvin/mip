import React, {useState} from "react";
import {Alert, Button, Form} from "react-bootstrap";
import {connect, useDispatch} from "react-redux";
import {useNavigate} from "react-router-dom";
import {login} from "../services/user/userActions";

function Login() {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState(null);
    const navigate = useNavigate();
    const dispatch = useDispatch();

    function validateForm() {
        return email.length > 0 && password.length > 0;
    }

    function handleSubmit(event) {
        event.preventDefault();
    }

    async function auth() {
        await dispatch(await login(email, password));
        if (await localStorage.isLoggedIn === 'true') {
            console.log("Success");
            navigate("/home");
            navigate(0);
        } else {
            console.log("Failure");
            setError("Not valid credentials!")
            setTimeout(() => setError(''), 3000);
        }
    }

    return (<div className="Login" style={{
        display: "flex", height: "70vh", justifyContent: "center", alignItems: "center"
    }}>
        <Form style={{maxWidth: "400px", margin: "0 auto"}} onSubmit={handleSubmit}>
            <h1 style={{textAlign: "center", paddingBottom: "20px"}}>Drugs And Rock&Roll</h1>
            {error && <Alert variant="danger">{error}</Alert>}
            <Form.Group size="lg" controlId="email">
                <Form.Label style={{paddingTop: "10px"}}><h5>Email</h5></Form.Label>
                <Form.Control style={{fontSize: "20px"}}
                              autoFocus
                              type="email"
                              value={email}
                              onChange={(e) => setEmail(e.target.value)}
                />
            </Form.Group>
            <Form.Group size="lg" controlId="password">
                <Form.Label style={{paddingTop: "10px"}}><h5>Password</h5></Form.Label>
                <Form.Control style={{fontSize: "20px"}}
                              type="password"
                              value={password}
                              onChange={(e) => setPassword(e.target.value)}
                />
            </Form.Group>
            <Button style={{marginTop: "20px"}} size="lg" type="submit" disabled={!validateForm()}
                    onClick={auth}>
                Login
            </Button>
            <Button style={{marginTop: "20px", marginLeft: "20px"}} size="lg" type="submit">
                Register Pharmacy
            </Button>
        </Form>
    </div>);
}


export default connect(null, null)(Login);
