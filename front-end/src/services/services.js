
import axios from "axios";

axios.defaults.baseURL = "http://localhost:5001/";

export default axios.create({
    baseURL: "http://localhost:5001/",
    headers: {
        "Content-type": "application/json",
    }
});

