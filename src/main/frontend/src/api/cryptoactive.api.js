import axios from "axios";

const host = "localhost:8080";


export const getQuotes = () => {
   const headers = {
    'Authorization': 'Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJuZWxAZ21haWwuY29tIiwiZXhwIjoxNjMzMjA1MDEwLCJpYXQiOjE2MzMxODcwMTB9.yIeeOnNxSpzKqe2rx08mBg7Pryw8onqrrKEtDBVgHOLfkl5kp_7S1uCLNF1pceeX5ZuFjwW71ddeDaA-rXdARA',
                                                                        'Content-Type': 'application/json',

                                                                    'access-control-allow-origin': 'http://localhost:3000',
                                                                    'Access-Control-Allow-Credentials':'true',
                                                                    'X-Requested-With':'XMLHttpRequest'
   }

 return  axios.get(`http://${host}/api/cryptoassets`, {}, {
       headers: headers
     })
     .then((response) => {

     })
     .catch((error) => {

     })





}
export const postRegister=(user)=>{
    console.log(user)
    return axios.post(`http://${host}/register`,user)
}

export const postLogin=(user)=>{
    console.log(user)
    return axios.post(`http://${host}/authenticate`,user)
}
export default {
    postRegister,
    getQuotes,
    postLogin

};