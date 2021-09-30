import axios from "axios";

const host = "localhost:8080";


export const getQuotes = () => {
    return axios.get(`http://${host}/api/cryptoassets`);
}

export default {
    getQuotes
};