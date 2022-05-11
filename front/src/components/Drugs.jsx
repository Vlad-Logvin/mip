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
import {deleteDrugById, findAllDrugs, saveDrug, updateDrug} from "../services/drug/drugActions";
import {findAllStorages} from "../services/storage/storageActions";

function Drugs() {
    const [error, setError] = useState(null);
    const [isSave, setIsSave] = useState(null);
    const [name, setName] = useState("");
    const [storages, setStorages] = useState(null);
    const [vendors, setVendors] = useState(null);
    const [success, setSuccess] = useState(null);
    const [modalError, setModalError] = useState(null);
    const [drugToSave, setDrugToSave] = useState({});
    const [currentPage, setCurrentPage] = useState(1);
    const [currentSize, setCurrentSize] = useState(10);
    const [totalPage, setTotalPage] = useState(1);
    const [drugs, setDrugs] = useState([]);
    const [items, setItems] = useState([]);
    const dispatch = useDispatch();
    const drugData = useSelector((state) => state.drug);
    const storageData = useSelector((state) => state.storage);
    const [show, setShow] = useState(false);
    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);

    useEffect(() => {
        findDrugs();
        findStorages();
        findVendors();
        initItems();
    }, [drugData]);

    async function findDrugs() {
        await dispatch(await findAllDrugs(localStorage.pharmacyId, name, currentPage, currentSize));
        const drugResponse = await drugData?.drugPromise;
        if (drugResponse?.content) {
            setDrugs(drugResponse.content);
            setTotalPage(drugResponse.totalPages);
            setError("");
        } else {
            setError("Error during finding all drugs");
        }
    }

    async function findStorages() {
        await dispatch(await findAllStorages(localStorage.pharmacyId, 1, 10000));
        const storageResponse = await storageData?.storagePromise;
        if (storageResponse?.content) {
            if (!storages) {
                setStorages(<Form.Select value={drugToSave.storageId} onChange={e => drugToSave.storageId = e.target.value}>
                    <option key=""></option>
                    {storageResponse.content?.length !== 0 ? (storageResponse.content.map((storage) =>
                        (<option key={storage.id}>{storage.id}</option>))) : ""}
                </Form.Select>);
                setModalError("")
            }
        } else {
            setModalError("Unable to create drug without storages");
        }
    }

    async function findVendors() {
        if (!vendors) {
            setVendors(<Form.Select value={drugToSave.vendorId} onChange={e => drugToSave.vendorId = e.target.value}>
                <option key=""></option>
                <option key="1">1</option>
            </Form.Select>)
        }
    }

    async function save() {
        dispatch(await saveDrug(drugToSave))
            .then((value) => {
                setSuccess("Drug saved successfully!");
                setError("")
            })
            .catch(error =>
                setError(error)
            )
        setDrugToSave({});
    }

    async function removeDrug(drugId) {
        dispatch(await deleteDrugById(drugId))
            .then((value) => {
                setSuccess("Drug deleted successfully!");
                setError("")
            })
            .catch(error =>
                setError(error)
            )
    }

    async function update() {
        dispatch(await updateDrug(drugToSave.id, drugToSave))
            .then((value) => {
                setSuccess("Drug updated successfully!");
                setError("")
            })
            .catch(error =>
                setError(error)
            )
        setDrugToSave({});
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
        <Modal show={show} onHide={() => {
            handleClose();
            setDrugToSave({});
            setModalError("")
        }}>
            <Form>
                <Modal.Header closeButton>
                    <Modal.Title>Add drug</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Alert variant="danger" show={modalError}>
                        {modalError}
                    </Alert>
                    <InputGroup>
                        <Form.Group style={{width: '100%'}}>
                            <Form.Label>Name</Form.Label>
                            <Form.Control
                                required
                                autoComplete="off"
                                type="text"
                                value={drugToSave.name}
                                onChange={(event) => drugToSave.name = event.target.value}
                                name="name"
                                placeholder="Enter name"
                            />
                        </Form.Group>
                        <Form.Group style={{width: '100%'}}>
                            <Form.Label>Price</Form.Label>
                            <Form.Control
                                required
                                autoComplete="off"
                                type="number"
                                value={drugToSave.price}
                                onChange={(event) => drugToSave.price = event.target.value}
                                name="price"
                                placeholder="Enter price"
                            />
                        </Form.Group>
                        <Form.Group style={{width: '100%'}}>
                            <Form.Label>Description</Form.Label>
                            <Form.Control
                                autoComplete="off"
                                type="text"
                                value={drugToSave.description}
                                onChange={(event) => drugToSave.description = event.target.value}
                                name="description"
                                placeholder="Enter description"
                            />
                        </Form.Group>
                        <Form.Group style={{width: '100%'}}>
                            <Form.Label>Quantity</Form.Label>
                            <Form.Control
                                required
                                autoComplete="off"
                                type="number"
                                value={drugToSave.quantity}
                                onChange={(event) => drugToSave.quantity = parseInt(event.target.value) > 1000 ? 1000 : (parseInt(event.target.value) < 1 ? 1 : parseInt(event.target.value))}
                                name="quantity"
                                placeholder="Enter quantity"
                            />
                        </Form.Group>
                        <Form.Group style={{width: "100%"}}>
                            <Form.Label>Storage</Form.Label>
                            {storages}
                        </Form.Group>
                        <Form.Group style={{width: "100%"}}>
                            <Form.Label>Vendor</Form.Label>
                            {vendors}
                        </Form.Group>
                    </InputGroup>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={handleClose}>
                        Close
                    </Button>
                    <Button variant="primary" onClick={() => {
                        if (drugToSave.name && drugToSave.price && drugToSave.quantity && drugToSave.storageId && drugToSave.vendorId) {
                            if (isSave) {
                                save();
                            } else {
                                update();
                            }
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
            <Card className={"bdrug bdrug-white bg-white text-dark"}>
                <Card.Header>
                    <div style={{float: "left"}}><h1>Drug List</h1>
                        <ButtonGroup>
                            <Button size="md" variant="success" onClick={() => {
                                handleShow();
                                setIsSave(true);
                            }}>
                                Add
                            </Button>
                        </ButtonGroup></div>
                    <div style={{float: "right"}}>
                        <Form className="d-flex">
                            <FormControl
                                type="search"
                                placeholder="Name"
                                className="me-2"
                                aria-label="Search"
                                value={name}
                                onChange={(e) => setName(e.target.value)}
                            />
                        </Form>
                    </div>


                </Card.Header>
                <Card.Body>
                    <Table striped hover>
                        <thead>
                        <tr>
                            <th>#</th>
                            <th>id</th>
                            <th>Name</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>Description</th>
                            <th>Storage Id</th>
                            <th>Vendor Name</th>
                            <th>Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        {drugs && drugs.length !== 0 ? (drugs.map((drug, index) => (<tr key={drug.id}>
                            <td>{index + 1 + (currentPage - 1) * currentSize}</td>
                            <td>{drug.id}</td>
                            <td>{drug.name}</td>
                            <td>{drug.price}</td>
                            <td>{drug.quantity}</td>
                            <td>{drug.description}</td>
                            <td>{drug.storage}</td>
                            <td>{drug.vendor.name}</td>
                            <td>
                                <ButtonGroup>
                                    <Button size="md" variant="info" onClick={() => {
                                        handleShow();
                                        setIsSave(false);
                                        drugToSave.id = drug.id;
                                        drugToSave.name = drug.name;
                                        drugToSave.price = drug.price;
                                        drugToSave.quantity = drug.quantity;
                                        drugToSave.description = drug.description;
                                        drugToSave.storageId = drug.storage;
                                        drugToSave.vendorId = drug.vendor.id;
                                        setVendors(null);
                                        setStorages(null);
                                    }}>
                                        Update
                                    </Button>
                                    <Button size="md" variant="danger" onClick={() => removeDrug(drug.id)}>
                                        Delete
                                    </Button>
                                </ButtonGroup>
                            </td>
                        </tr>))) : (<tr align="center">
                            <td colSpan="9">No Drugs Available</td>
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
                            <FormControl width="100px" type="number" max="30" min="1" name="drugPerPage"
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

export default connect(null, null)(Drugs);
