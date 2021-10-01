import React ,{useState}from "react";
import {useHistory} from "react-router-dom";
import {Form,Button} from 'react-bootstrap';
import { postLogin } from "../api/cryptoactive.api"

const Login = () => {

 const [datos, setDatos] = useState({
        username:'',
        password:''

    })
    const handleInputChange = (event) => {
         console.log(event.target.name)
         console.log(event.target.value)
        setDatos({
            ...datos,
            [event.target.name] : event.target.value
        })
        console.log(localStorage)

    }



    const login = () => {
        postLogin(datos).then((result) => {

            localStorage.setItem("token", result.data.token);
            console.log(localStorage.getItem("Token"))

            alert("logeado con exito")
        })
            .catch(console.log);
    };

    return (
        <>
            <Form onSubmit={login}>
                <Form.Group className="mb-3" controlId="formBasicEmail" onChange={handleInputChange}>
                    <Form.Label>Email address</Form.Label>
                    <Form.Control type="email" placeholder="Enter email" name="username"/>
                    <Form.Text className="text-muted">
                        We'll never share your email with anyone else.
                    </Form.Text>
                </Form.Group>

                <Form.Group className="mb-3" controlId="formBasicPassword" onChange={handleInputChange}>
                    <Form.Label>Password</Form.Label>
                    <Form.Control type="password" placeholder="Password" name="password"/>
                </Form.Group>
                <Form.Group className="mb-3" controlId="formBasicCheckbox">
                    <Form.Check type="checkbox" label="Check me out" />
                </Form.Group>
                <Button variant="primary" type="submit">
                    Submit
                </Button>
            </Form>
        </>
    )
}

export default Login;