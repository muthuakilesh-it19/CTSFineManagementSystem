import React, { useState ,useEffect} from 'react';
import Helmet from '../components/Helmet';
import ViewVehicles from '../components/ViewVehicles';
import AddVehicles from '../components/AddVehicle';
import Card from 'react-bootstrap/Card';
import SearchByPlateNumber from '../components/SearchByPlateNumber';
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
import axios from 'axios';
import Header from '../components/Header';

import Ava from '../assets/Images/Ava.png';
export default function UserProfile() {
  let [showUpdateVehicles, setshowUpdateVehicles] = useState(false);
  const handleOnClickUpdateVehicles = () => {
    setshowUpdateVehicles(!showUpdateVehicles);
  };

  let [showAddVehicles, setshowAddVehicles] = useState(false);
  const handleOnClickAddVehicles = () => {
    setshowAddVehicles(!showAddVehicles);
  };

  let [showViewVehicles, setshowViewVehicles] = useState(false);
  const handleOnClickViewVehicles = () => {
    setshowViewVehicles(!showViewVehicles);
  };

  const [user, setUser] = useState('');
  const token = localStorage.getItem("token");
  const userId=localStorage.getItem("userId");
  useEffect(() => {
    const fetchUserProfile = async () => {
      try {
        // Make a GET request to the user profile API endpoint
        const response = await axios.get(`http://localhost:9096/user/${userId}`, {
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
    <Helmet title="userprofile">
      <Header isLogged={false} />
      <Card>
        <div className="mx-auto text-center" style={{ height: '140px' }}>
          <Card.Body>
            <Card.Title style={{ color: 'red' }} class="h1">
              Welcome to profile section
            </Card.Title>
            <Card.Text>View and Modify your profile here </Card.Text>
          </Card.Body>
        </div>
        <MDBRow>
        <MDBCol sm='2'>
        </MDBCol>
          <MDBCol sm="2">
            {/* <MDBCard > */}
            <div className=" card aligns-items-center justify-content-center  text-center mx-auto ">
              <div className="card-body">
                <img
                  src={Ava}
                  alt="avatar"
                  className="rounded-circle img-fluid"
                />
                <h5 className="my-2">{user.userName}</h5>
                
              </div>
            </div>
          </MDBCol>
          <MDBCol>
            {/* <MDBCard > */}

            <div class="card mb-4">
              <br />
              <div class="card-body">
                <div class="row">
                  <div class="col-sm-3">
                    <p class="mb-0">User Name :</p>
                  </div>
                  <div class="col-sm-9">
                    <p class="text-muted mb-0">{user.userName}</p>
                  </div>
                </div>
                <hr />
                <div class="row">
                  <div class="col-sm-3">
                    <p class="mb-0">Email :</p>
                  </div>
                  <div class="col-sm-9">
                    <p class="text-muted mb-0">{user.email}</p>
                  </div>
                </div>
                <hr />
                
                <div class="row">
                  <div class="col-sm-3">
                    <p class="mb-0">Roles :</p>
                  </div>
                  <div class="col-sm-9">
                    <p class="text-muted mb-0">{user.roles}</p>
                  </div>
                </div>
                <hr />
              </div>
            </div>

            {/* </MDBCard> */}
          </MDBCol>
          <MDBCol sm='2'>
        </MDBCol>
        </MDBRow>
        <MDBRow>
          <MDBCol>
            {/* <MDBCard > */}
            <MDBCardBody>
              <div className="mx-auto text-center">
                <MDBCardTitle>View Your vehicles</MDBCardTitle>
                <MDBBtn
                  color="danger"
                  onClick={() => handleOnClickViewVehicles()}
                >
                  Click Here
                </MDBBtn><MDBCardBody></MDBCardBody>
              </div>
              {showViewVehicles ? <ViewVehicles /> : <div></div>}
            </MDBCardBody>
            {/* </MDBCard> */}
          </MDBCol>
          <hr/>
          </MDBRow>
        <MDBRow>
          
          <MDBCol>

          <MDBCardBody>
              <div className="mx-auto text-center">
                <MDBCardTitle>Add Your vehicle</MDBCardTitle>
                <MDBBtn
                  color="danger"
                  onClick={() => handleOnClickAddVehicles()}
                >
                  Click Here
                </MDBBtn><MDBCardBody></MDBCardBody>
              </div>
              {showAddVehicles ? <AddVehicles /> : <div></div>}
            </MDBCardBody>
            {/* </MDBCard> */}
          </MDBCol>
        </MDBRow>
<hr />
        <MDBRow>
          
          <MDBCol>

          <MDBCardBody>
              <div className="mx-auto text-center">
                <MDBCardTitle>Modify Your vehicle</MDBCardTitle>
                <MDBBtn
                  color="danger"
                  onClick={() => handleOnClickUpdateVehicles()}
                >
                  Click Here
                </MDBBtn><MDBCardBody></MDBCardBody>
              </div>
              {showUpdateVehicles ? <SearchByPlateNumber /> : <div></div>}
            </MDBCardBody>
            {/* </MDBCard> */}
          </MDBCol>
        </MDBRow>
      </Card> 
    </Helmet>
  );
}
