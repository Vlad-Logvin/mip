import {Container, Nav, Navbar, NavDropdown} from "react-bootstrap";
import {connect, useDispatch} from "react-redux";
import {logoutUser} from "../services/user/userActions";
import {useNavigate} from "react-router-dom";

function Header() {
    const dispatch = useDispatch();
    const navigate = useNavigate();

    async function logout() {
        await dispatch(await logoutUser());
        navigate(0);
    }

    return (<Navbar bg="light" variant="light">
        <Container>
            <Navbar.Brand href="/home">Home</Navbar.Brand>
            <Nav className="me-auto">
                <Nav.Link href="/storages">Storages</Nav.Link>
                <Nav.Link href="/drugs">Drugs</Nav.Link>
                {localStorage.role === 'ROLE_PHARMACY_OWNER' && <Nav.Link href="/pharmacists">Pharmacists</Nav.Link>}
                <Nav.Link href="/receipts">Receipts</Nav.Link>
                <Nav.Link href="/reports">Reports</Nav.Link>
            </Nav>
            <Nav className="justify-content-end">
                <Nav.Link className="justify-content-end" href="/set_order">Set Order</Nav.Link>
                <NavDropdown title={localStorage.username}>
                    <NavDropdown.Item onClick={logout}>Logout</NavDropdown.Item>
                </NavDropdown>
            </Nav>
        </Container>
    </Navbar>);
}

export default connect(null, null)(Header);

