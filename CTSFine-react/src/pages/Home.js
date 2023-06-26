import React,{useState} from 'react';

import Helmet from '../components/Helmet';
import Carousel from '../components/Carousel';
import Header from '../components/Header';
import Login from '../components/Login';
import FluidImage from '../components/FluidImage';
import Button from 'react-bootstrap/Button';
import Card from 'react-bootstrap/Card';
import Traffic from '../assets/Images/traffic1.jpg';
import { Container, Row, Col } from 'reactstrap';
import {
  MDBCarousel,
  MDBCarouselItem,
  MDBCard,
  MDBCardBody,
  MDBCardTitle,
  MDBCardText,
  MDBCardImage,
  MDBBtn,
  MDBRipple,
  MDBCol,
  MDBRow,
} from 'mdb-react-ui-kit';

const Home = () => {
  function handleClick() {
    window.scrollTo({
      top:1450,
      behaviour:'smooth',

    });
  };
  return (
    <>
    
      <Helmet title="Home">
      <Header isLogged={true}/>
        <Card>
          <div className="mx-auto text-center" style={{ height: '180px' }}>
            <Card.Body>
              <Card.Title style={{ color: 'red' }} class="h1">
                Fine Management System
              </Card.Title>
              <Card.Text>
                Easily access your fines and get ahead of penalties
              </Card.Text>
              <Button variant="danger" onClick={handleClick}>Register Today</Button>
            </Card.Body>
          </div>

          {/* <MDBCard> */}
          <MDBRow>
            <MDBCol sm='2'></MDBCol>
            <MDBCol sm="5">
              {/* <MDBCard > */}
              <MDBCardImage
                src={Traffic}
                fluid
                alt="..."
                style={{ width: '100%' }}
              />

              {/* </MDBCard> */}
            </MDBCol>

            <MDBCol >
              {/* <div style={{width:'100%', height:'100%'}}> */}
              {/* <MDBCard style={{ width: '100%', height: '100%' }}> */}
              
              <MDBCardBody>
                <div className="mx-auto text-center">
                  <MDBCardTitle>No More Penalties</MDBCardTitle>
                  <MDBCardText>
                    Get ahead of your penalties and manage your fine details
                    efficiently and much more in a simpler way  
                    <br /> Make on demand payments and say no to excess
                    penalties on your vehicles
                    <br /> Manage your violation details and avoid unwanted
                    worry about your fines.
                  </MDBCardText>
                  <hr/>
                  <MDBCardTitle>Efficiently Manage Fines with our Fine Management System</MDBCardTitle>
                  <MDBCardText>
                    Welcome to our Fine Management System, a comprehensive solution designed
                    to streamline fine management and ensure efficient processing. Our system simplifies
                    the management of fines, providing you with a centralized platform to handle fine issuance, tracking and payment.
                  </MDBCardText>
                  
                  
                </div>
              </MDBCardBody>

              {/* </div> */}
            </MDBCol>
            <MDBCol sm='2'></MDBCol>
          </MDBRow>
          {/* </MDBCard> */}
        <Card>
        <FluidImage />
        </Card>
        </Card>
        <MDBRow>
          <MDBCol sm="6">
            <Carousel />
          </MDBCol>
          <MDBCol sm="">
            <Login />
          </MDBCol>
          <br />
        </MDBRow>
      </Helmet>
    </>
  );
};

export default Home;
