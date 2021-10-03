import React, { useState } from 'react'
import { Form, Button,NavDropdown,Nav,Container,NavBar, NavItem } from 'react-bootstrap';
import './login.css';

import NavigationBar from '../components/NavigationBar';

function Login() {

  const [email, setEmail] = useState("")
  const [password, setPassword] = useState("")


  return (
    <div>
    <NavigationBar/>
    <Form className="formulario">
      <Form.Group className="mb-3" controlId="formBasicEmail">
        <Form.Label>Email</Form.Label>
        <Form.Control type="email" placeholder="Ingrese su email" />
        <Form.Text className="text-muted">
          Nunca revele su contraseña
        </Form.Text>
      </Form.Group>

      <Form.Group className="mb-3" controlId="formBasicPassword">
        <Form.Label>Password</Form.Label>
        <Form.Control type="password" placeholder="Ingrese su password" />
      </Form.Group>
      <Form.Group className="mb-3" controlId="formBasicCheckbox">
        <Form.Check type="checkbox" label="Mostrar contraseña" />
      </Form.Group>
      <Button variant="primary" type="submit">
        Submit
      </Button>
    </Form>



    </div>
  )
}

export default Login;
