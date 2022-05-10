import {connect, useDispatch, useSelector} from "react-redux";
import Header from "./Header";
import {Alert, Button, ButtonGroup, Card, Table} from "react-bootstrap";
import {useState} from "react";
import {findAllStorages} from "../services/storage/storageActions";
import localStorage from "redux-persist/es/storage";

function Storages() {
    const [error, setError] = useState(null);
    const [currentPage, setCurrentPage] = useState(1);
    const [currentSize, setCurrentSize] = useState(10);
    const [storages, setStorages] = useState([]);
    const dispatch = useDispatch();
    const storageData = useSelector((state) => state.storage);


    async function findStorages() {
        await dispatch(await findAllStorages(localStorage.pharmacyId, currentPage, currentSize));
        const storageResponse = await storageData.storagePromise;
        if (storageResponse && storageResponse.content) {
            setStorages(storageResponse.content);
        } else {
            setError("Error during finding all storages");
        }
    }

    return (<div>
        <Header/>

        <div>
            <Alert variant="danger" show={error}>
                {error}
            </Alert>
            <Card className={"bstorage bstorage-white bg-white text-dark"}>
                <Card.Header onClick={findStorages}>Storage List</Card.Header>
                <Card.Body>
                    <Table striped bstorageed hover>
                        <thead>
                        <tr>
                            <th>#</th>
                            <th>Storage Address</th>
                            <th>Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        {storages && storages.length !== 0 ? (
                            storages.map((storage) => (
                                <tr key={storage.id}>
                                    <td>{storage.id}</td>
                                    <td>{storage.pharmacyAddress}</td>
                                    <td>
                                        <ButtonGroup>
                                            <Button size="md" variant="danger">
                                                Delete
                                            </Button>
                                        </ButtonGroup>
                                    </td>
                                </tr>
                            ))
                        ) : (
                            <tr align="center">
                                <td colSpan="6">No Storages Available</td>
                            </tr>
                        )}
                        </tbody>
                    </Table>
                </Card.Body>
                {/*<Card.Footer>*/}
                {/*    <div style={{float: "left"}}>*/}
                {/*        <InputGroup>*/}
                {/*            <Form.Label column="lg" lg={13} style={{marginRight: "1em"}}>*/}
                {/*                Size*/}
                {/*            </Form.Label>*/}
                {/*            <FormControl*/}
                {/*                style={pageNumCss}*/}
                {/*                width="100px"*/}
                {/*                type="number"*/}
                {/*                max="30"*/}
                {/*                min="1"*/}
                {/*                name="userPerPage"*/}
                {/*                value={userPerPage}*/}
                {/*                onKeyUp={this.changePage}*/}
                {/*                onChange={this.changePage}*/}
                {/*                onClick={this.changePage}*/}
                {/*            />*/}
                {/*        </InputGroup>*/}
                {/*    </div>*/}
                {/*    <div style={{float: "right"}}>*/}
                {/*        <InputGroup>*/}
                {/*            <InputGroup.Prepend>*/}
                {/*                <Button*/}
                {/*                    type="button"*/}
                {/*                    variant="outline-info"*/}
                {/*                    style={buttonWidthCss}*/}
                {/*                    disabled={currentPage === 1}*/}
                {/*                    onClick={this.previousPage}*/}
                {/*                >*/}
                {/*                    Previous*/}
                {/*                </Button>*/}
                {/*            </InputGroup.Prepend>*/}
                {/*            <FormControl*/}
                {/*                style={pageNumCss}*/}
                {/*                type="number"*/}
                {/*                max="1000"*/}
                {/*                min="1"*/}
                {/*                value={currentPage}*/}
                {/*                name="currentPage"*/}
                {/*                onKeyUp={this.changePage}*/}
                {/*                onChange={this.changePage}*/}
                {/*                onClick={this.changePage}*/}
                {/*            />*/}
                {/*            <InputGroup.Append>*/}
                {/*                <Button*/}
                {/*                    type="button"*/}
                {/*                    variant="outline-info"*/}
                {/*                    style={buttonWidthCss}*/}
                {/*                    onClick={this.nextPage}*/}
                {/*                >*/}
                {/*                    Next*/}
                {/*                </Button>*/}
                {/*            </InputGroup.Append>*/}
                {/*        </InputGroup>*/}
                {/*    </div>*/}
                {/*</Card.Footer>*/}
            </Card>
        </div>
    </div>)
}

export default connect(null, null)(Storages);
