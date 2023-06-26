import React from 'react';
import Helmet from '../components/Helmet';
import Card from 'react-bootstrap/Card';
import AddViolation from '../components/AddViolation';
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
import OIP from '../assets/Images/OIP.jpeg';
import Header from '../components/Header';
export default function OfficerDashboard(){

  return(<>
  <Helmet title="userdashboard">
  <Header isLogged={false} loc={'officer'}/>
        <Card>
          <div className="mx-auto text-center" style={{ height: '140px' }}>
            <Card.Body>
              <Card.Title style={{ color: 'red' }} class="h1">
                Hello, Officer
              </Card.Title>
              <Card.Text>Assign Violations and Fines here</Card.Text>
            </Card.Body>
          </div>
          <MDBRow>
          <MDBCol sm="2">
            </MDBCol>
            <MDBCol sm="2">
              {/* <MDBCard > */}
              <MDBCardImage
                src={OIP}
                fluid
                alt="..."
                style={{ width: '100%' }}
              />
            </MDBCol>
            <MDBCol>
              <AddViolation />
              </MDBCol>
              <MDBCol sm="2">
            </MDBCol>
            </MDBRow>
          </Card>
          </Helmet>
  </>);
}