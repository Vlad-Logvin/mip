import {connect, useDispatch, useSelector} from "react-redux";
import Header from "./Header";
import {
    Alert,
    Button,
    ButtonGroup,
    Card,
    Form,
    FormControl,
    InputGroup,
    Modal,
    Pagination,
    Table
} from "react-bootstrap";
import {useEffect, useState} from "react";
import {deletePharmacistById, findAllPharmacists, savePharmacist} from "../services/pharmacist/pharmacistActions";

function Pharmacists() {
    const [error, setError] = useState(null);
    const [success, setSuccess] = useState(null);
    const [modalError, setModalError] = useState(null);
    const [pharmacistToSave, setPharmacistToSave] = useState({});
    const [currentPage, setCurrentPage] = useState(1);
    const [currentSize, setCurrentSize] = useState(10);
    const [totalPage, setTotalPage] = useState(1);
    const [pharmacists, setPharmacists] = useState([]);
    const [items, setItems] = useState([]);
    const dispatch = useDispatch();
    const pharmacistData = useSelector((state) => state.pharmacist);
    const [show, setShow] = useState(false);
    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);

    useEffect(() => {
        findPharmacists();
        initItems();
    }, [pharmacistData]);

    async function findPharmacists() {
        await dispatch(await findAllPharmacists(localStorage.pharmacyId, currentPage, currentSize));
        const pharmacistResponse = await pharmacistData?.pharmacistPromise;
        if (pharmacistResponse?.content) {
            setPharmacists(pharmacistResponse.content);
            setTotalPage(pharmacistResponse.totalPages);
            setError("");
        } else {
            setError("Error during finding all pharmacists");
        }
    }

    async function save() {
        pharmacistToSave.pharmacyId = await localStorage.pharmacyId;
        dispatch(await savePharmacist(pharmacistToSave))
            .then((value) => {
                setSuccess("Pharmacist saved successfully!");
                setError("")
            })
            .catch(error =>
                setError(error)
            )
        setPharmacistToSave({});
    }

    async function removePharmacist(pharmacistId) {
        dispatch(await deletePharmacistById(pharmacistId))
            .then((value) => {
                setSuccess("Pharmacist deleted successfully!");
                setError("")
            })
            .catch(error =>
                setError(error)
            )
    }

    async function initItems() {
        let number;
        if (currentPage < totalPage - 2) {
            number = currentPage > 5 ? currentPage - 2 : 1;
        } else {
            number = currentPage > 5 ? totalPage - 4 : 1;
        }
        const items = [];
        while (number <= totalPage && items.length < 5) {
            const i = number;
            items.push(<Pagination.Item key={number} active={number === currentPage}
                                        onClick={() => setCurrentPage(i)}>
                {number}
            </Pagination.Item>);
            number++;
        }
        setItems(items);
    }

    return (<div>
        <Modal show={show} onHide={handleClose}>
            <Form>
                <Modal.Header closeButton>
                    <Modal.Title>Add pharmacist</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Alert variant="danger" show={modalError}>
                        {modalError}
                    </Alert>
                    <InputGroup>
                        <Form.Group style={{width: '100%'}}>
                            <Form.Label>Pharmacy Address</Form.Label>
                            <Form.Control
                                required
                                autoComplete="off"
                                type="text"
                                value={pharmacistToSave.pharmacyAddress}
                                onChange={(event) => pharmacistToSave.pharmacyAddress = event.target.value}
                                name="pharmacyAddress"
                                placeholder="Enter pharmacy address"
                            />
                        </Form.Group>
                    </InputGroup>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={handleClose}>
                        Close
                    </Button>
                    <Button variant="primary" onClick={() => {
                        if (pharmacistToSave.pharmacyAddress) {
                            save();
                            setModalError("");
                            handleClose();
                        } else {
                            setModalError("Please, fill all fields!");
                        }
                    }}>
                        Save Changes
                    </Button>
                </Modal.Footer>
            </Form>
        </Modal>
        <Header/>
        <div>
            <Alert variant="danger" show={error}>
                {error}
            </Alert>
            <Alert variant="success" show={success}>
                {success}
            </Alert>
            <Card className={"bpharmacist bpharmacist-white bg-white text-dark"}>
                <Card.Header><h1>Pharmacist List</h1>
                    <ButtonGroup>
                        <Button size="md" variant="success" onClick={handleShow}>
                            Add
                        </Button>
                    </ButtonGroup>

                </Card.Header>
                <Card.Body>
                    <Table striped hover>
                        <thead>
                        <tr>
                            <th>#</th>
                            <th>id</th>
                            <th>Pharmacy Address</th>
                            {localStorage.role === 'ROLE_PHARMACY_OWNER' ? <th>Actions</th> : ""}
                        </tr>
                        </thead>
                        <tbody>
                        {pharmacists && pharmacists.length !== 0 ? (pharmacists.map((pharmacist, index) => (<tr key={pharmacist.id}>
                            <td>{index + 1 + (currentPage - 1) * currentSize}</td>
                            <td>{pharmacist.id}</td>
                            <td>{pharmacist.pharmacyAddress}</td>
                            {localStorage.role === 'ROLE_PHARMACY_OWNER' ? <td>
                                <ButtonGroup>
                                    <Button size="md" variant="danger" onClick={() => removePharmacist(pharmacist.id)}>
                                        Delete
                                    </Button>
                                </ButtonGroup>
                            </td> : ""}
                        </tr>))) : (<tr align="center">
                            <td colSpan="6">No Pharmacists Available</td>
                        </tr>)}
                        </tbody>
                    </Table>
                </Card.Body>
                <Card.Footer>
                    <div style={{float: "left"}}>
                        <Pagination>
                            <Pagination.First onClick={() => setCurrentPage(1)}/>
                            <Pagination.Prev onClick={() => setCurrentPage(currentPage > 1 ? currentPage - 1 : 1)}/>
                            <Pagination>{items}</Pagination>
                            <Pagination.Next
                                onClick={() => setCurrentPage(currentPage < totalPage ? currentPage + 1 : totalPage)}/>
                            <Pagination.Last onClick={() => setCurrentPage(totalPage)}/>
                        </Pagination>
                    </div>
                    <div style={{float: "right"}}>
                        <InputGroup>
                            <FormControl width="100px" type="number" max="30" min="1" name="pharmacistPerPage"
                                         value={currentSize}
                                         onChange={(event) => {
                                             let targetSize;
                                             if (event.target.value) {
                                                 if (event.target.value > 30) {
                                                     targetSize = 30;
                                                 } else if (event.target.value < 1) {
                                                     targetSize = 1;
                                                 } else {
                                                     targetSize = event.target.value;
                                                 }
                                             } else {
                                                 targetSize = 10;
                                             }
                                             if (currentPage * targetSize > totalPage * currentSize) {
                                                 setCurrentPage(1);
                                             }
                                             setCurrentSize(targetSize);
                                         }}
                            />
                        </InputGroup>
                    </div>
                </Card.Footer>
            </Card>
        </div>
    </div>)
}

export default connect(null, null)(Pharmacists);
