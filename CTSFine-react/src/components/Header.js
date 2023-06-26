import React, { useState, useRef } from 'react';
import Button from 'react-bootstrap/Button';
import Container from 'react-bootstrap/Container';
import Form from 'react-bootstrap/Form';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import NavDropdown from 'react-bootstrap/NavDropdown';
import Offcanvas from 'react-bootstrap/Offcanvas';
import {useNavigate} from 'react-router-dom';
const Header = ({ isLogged,loc }) => {
  const [showNavSecond, setShowNavSecond] = useState(true);
  const titleRef = useRef();
  let navigate = useNavigate(); 
  
  function handleClick() {
    window.scrollTo({
      top:1450,
      behaviour:'smooth',
    });
  };
  return (
    <>
      {['xl'].map((expand) => (
        <Navbar
          key={expand}
          expand={expand}
          style={{ height: '80px' }}
          bg="dark"
          data-bs-theme="dark"
        >
          <Container fluid>
            <Navbar.Brand href="#">Fine Management System</Navbar.Brand>
            <Button variant="danger">
              <text className="text-align-center"></text>
              <Navbar.Toggle
                aria-controls={`offcanvasNavbar-expand-${expand}`}
              />
            </Button>
            <Navbar.Offcanvas
              id={`offcanvasNavbar-expand-${expand}`}
              aria-labelledby={`offcanvasNavbarLabel-expand-${expand}`}
              placement="end"
            >
              <Offcanvas.Header closeButton>
                <Offcanvas.Title id={`offcanvasNavbarLabel-expand-${expand}`}>
                  Fine Management System
                </Offcanvas.Title>
              </Offcanvas.Header>
              {isLogged ? (
                <Offcanvas.Body className="justify-content-end">
                  <Button variant="outline-success" onClick={handleClick}>
                    Signin
                  </Button>
                  <vr/>
                  <br />
                 <Button variant="outline-primary" onClick={handleClick}>Signup</Button>
                </Offcanvas.Body>
              ) : (
                <Offcanvas.Body>
                  <Nav className="justify-content-end flex-grow-1 pe-3">
                    <Nav.Link href="/home">Home</Nav.Link>
                    {loc==='officer'?
                    <Nav.Link href="/officerprofile">Profile</Nav.Link>:<Nav.Link href="/userprofile">Profile</Nav.Link>
                    }</Nav>
                  <Button variant="outline-danger" onClick={()=>navigate('/home')}>LogOut</Button>
                </Offcanvas.Body>
              )}
            </Navbar.Offcanvas>
          </Container>
        </Navbar>
      ))}
    </>
  );
};
export default Header;
