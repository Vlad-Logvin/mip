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
import {deleteStorageById, findAllStorages, saveStorage} from "../services/storage/storageActions";

function Storages() {
    const [error, setError] = useState(null);
    const [success, setSuccess] = useState(null);
    const [modalError, setModalError] = useState(null);
    const [storageToSave, setStorageToSave] = useState({});
    const [currentPage, setCurrentPage] = useState(1);
    const [currentSize, setCurrentSize] = useState(10);
    const [totalPage, setTotalPage] = useState(1);
    const [storages, setStorages] = useState([]);
    const [items, setItems] = useState([]);
    const dispatch = useDispatch();
    const storageData = useSelector((state) => state.storage);
    const [show, setShow] = useState(false);
    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);

    useEffect(() => {
        findStorages();
        initItems();
    }, [storageData]);

    async function findStorages() {
        await dispatch(await findAllStorages(localStorage.pharmacyId, currentPage, currentSize));
        const storageResponse = await storageData?.storagePromise;
        if (storageResponse?.content) {
            setStorages(storageResponse.content);
            setTotalPage(storageResponse.totalPages);
            setError("");
        } else {
            setError("Error during finding all storages");
        }
    }

    async function save() {
        storageToSave.pharmacyId = await localStorage.pharmacyId;
        dispatch(await saveStorage(storageToSave))
            .then((value) => {
                setSuccess("Storage saved successfully!");
                setError("")
            })
            .catch(error =>
                setError(error)
            )
        setStorageToSave({});
    }

    async function removeStorage(storageId) {
        dispatch(await deleteStorageById(storageId))
            .then((value) => {
                setSuccess("Storage deleted successfully!");
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
                    <Modal.Title>Add storage</Modal.Title>
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
                                value={storageToSave.pharmacyAddress}
                                onChange={(event) => storageToSave.pharmacyAddress = event.target.value}
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
                        if (storageToSave.pharmacyAddress) {
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
            <Card className={"bstorage bstorage-white bg-white text-dark"}>
                <Card.Header><h1>Storage List</h1>
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
                        {storages && storages.length !== 0 ? (storages.map((storage, index) => (<tr key={storage.id}>
                            <td>{index + 1 + (currentPage - 1) * currentSize}</td>
                            <td>{storage.id}</td>
                            <td>{storage.pharmacyAddress}</td>
                            {localStorage.role === 'ROLE_PHARMACY_OWNER' ? <td>
                                <ButtonGroup>
                                    <Button size="md" variant="danger" onClick={() => removeStorage(storage.id)}>
                                        Delete
                                    </Button>
                                </ButtonGroup>
                            </td> : ""}
                        </tr>))) : (<tr align="center">
                            <td colSpan="6">No Storages Available</td>
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
                            <FormControl width="100px" type="number" max="30" min="1" name="storagePerPage"
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

export default connect(null, null)(Storages);
