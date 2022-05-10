import {connect} from "react-redux";
import Header from "./Header";

function Home() {
    return (<div>
        <Header/>
        <h1 style={{marginTop: '20px', marginLeft: '20px'}}>Drugs And Rock&Roll</h1>
    </div>)
}

export default connect(null, null)(Home);
