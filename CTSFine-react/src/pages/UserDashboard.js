import React, { useState, useEffect } from 'react';
import Helmet from '../components/Helmet';
import Card from 'react-bootstrap/Card';
import Button from 'react-bootstrap/Button';
import ViolationTable from '../components/ViolationTable';
import {

  MDBCardBody,
  MDBCardTitle,

  MDBCardImage,
  MDBBtn,

  MDBCol,
  MDBRow,
} from 'mdb-react-ui-kit';
import Chart from '../assets/Images/Chart.png';
import PendingFinesTable from '../components/PendingFinesTable';
import axios from 'axios';
import Header from '../components/Header';

// Your JavaScript code here

export default function UserDashboard() {

  const [user, setUser] = useState('');

  let [showTableFines, setShowTableFines] = useState(false);
  let [penaltiesPaidCount, setPenaltiesPaidCount] = useState(0);
  let [pendingFinesCount, setPendingFinesCount] = useState(0);

  const handleOnClickFines = () => {
    setShowTableFines(!showTableFines);
  };
  let [showTableViolations, setShowTableViolations] = useState(false);

  const handleOnClickViolations = () => {
    setShowTableViolations(!showTableViolations);
  };
 
  const userId = localStorage.getItem("userId");

  useEffect(() => {
    const fetchUserProfile = async () => {
      try {
        // Make a GET request to the user profile API endpoint
        const response = await axios.get(`http://localhost:9097/fines/count/${userId}`, {
          headers: {
            Authorization: `Bearer ${localStorage.getItem('token')}`, // Attach the JWT token in the Authorization header
          },
        });
        console.log(response.data);
        setUser(response.data); // Set the user data
      } catch (error) {
        console.error(error); // Handle any error
      }
    }

    fetchUserProfile();
   
  }, []);

  return (
    <>
      <Helmet title="userdashboard">
      <Header isLogged={false}/>
        <Card>
          <div className="mx-auto text-center" style={{ height: '140px' }}>
            <Card.Body>
              <Card.Title style={{ color: 'red' }} class="h1">
                Hello,User
              </Card.Title>
              <Card.Text>Manage your fines and vehicles here</Card.Text>
            </Card.Body>
          </div>
          <MDBRow>
            <MDBCol sm='2'>
            </MDBCol>
            <MDBCol sm="2">
              {/* <MDBCard > */}
              <MDBCardImage
                src={Chart}
                fluid
                alt="..."
                style={{ width: '100%' }}
              />

            </MDBCol>

            <MDBCol>
              {/* <MDBCard > */}
              <MDBCardBody>
                <MDBCardBody>
                  <br />
                </MDBCardBody>
                <div
                  className=" d-flex aligns-items-center justify-content-center card text-center  mx-auto"
                  style={{ color: 'blue' }}
                >
                  <MDBCardTitle>Penalties Paid</MDBCardTitle>

                  <MDBCardBody>{user.finesPaid}</MDBCardBody>
                </div>
              </MDBCardBody>
              {/* </MDBCard> */}
            </MDBCol>

            <MDBCol>
              {/* <div style={{width:'100%', height:'100%'}}> */}
              {/* <MDBCard style={{ width: '100%', height: '100%' }}> */}
              <MDBCardBody>
                <br />
              </MDBCardBody>
              <MDBCardBody>
                <div
                  className="d-flex aligns-items-center justify-content-center card text-center mx-auto"
                  style={{ color: 'red' }}
                >
                  <MDBCardTitle>Pending Fines</MDBCardTitle>
                  <MDBCardBody>{user.pendingFines}</MDBCardBody>
                </div>
              </MDBCardBody>

              {/* </div> */}
            </MDBCol>
            <MDBCol sm='2'>
            </MDBCol>
          </MDBRow>
          <hr />
          <MDBRow>
            <MDBCol sm='3'></MDBCol>
            <MDBCol>
              {/* <MDBCard > */}
              <MDBCardBody>

                <div className="mx-auto text-center">
                  <MDBCardTitle>View Your violations</MDBCardTitle>
                  <MDBBtn
                    color="danger"
                    onClick={() => handleOnClickViolations()}
                  >
                    Click Here
                  </MDBBtn>
                </div>
              </MDBCardBody>
              {/* </MDBCard> */}
              {showTableViolations ? <ViolationTable/> : <div></div>}
            </MDBCol>
            <MDBCol sm='3'></MDBCol>
          </MDBRow>
          <hr />
          <MDBRow>
          <MDBCol sm='3'></MDBCol>
            <MDBCol>
              {/* <div style={{width:'100%', height:'100%'}}> */}
              {/* <MDBCard style={{ width: '100%', height: '100%' }}> */}

              <MDBCardBody>
                <div className="mx-auto text-center px-md-5">
                  <MDBCardTitle>Get Pending Fines</MDBCardTitle>

                  <MDBBtn color="danger" onClick={() => handleOnClickFines()}>
                    Get
                  </MDBBtn>
                </div>
              </MDBCardBody>

              {/* </div> */}
              {showTableFines ? (
                <div className="px-md-auto">
                  <PendingFinesTable  />
                </div>
              ) : (
                <div></div>
              )}
            </MDBCol>
            <MDBCol sm='3'></MDBCol>
          </MDBRow>
        </Card>

      </Helmet>
    </>
  );

}

