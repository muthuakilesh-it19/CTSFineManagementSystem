import Card from 'react-bootstrap/Card';
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
  MDBRow,MDBTable,MDBTableHead,MDBTableBody
} from 'mdb-react-ui-kit';
import React, { useState ,useEffect} from 'react';
import Helmet from '../components/Helmet';
import Header from '../components/Header';
import axios from 'axios';

import Chief from '../assets/Images/Chief.png';
export default function OfficerProfile() {
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
  const[allUser,setAllUser]=useState([]);
  const fetchAllUsers = async () => {
    try {
      // Make a GET request to the user profile API endpoint
      const response = await axios.get(`http://localhost:9096/user/`, {
        headers: {
          Authorization: `Bearer ${localStorage.getItem('token')}`, // Attach the JWT token in the Authorization header
        },
      });
      console.log(response.data);
      setAllUser(response.data); // Set the user data
    } catch (error) {
      console.error(error); // Handle any error
    }
  }
  let i=0;
  return (
    <Helmet title="userprofile">
      <Header isLogged={false} loc={'officer'}/>
      <Card>
        <div className="mx-auto text-center" style={{ height: '140px' }}>
          <Card.Body>
            <Card.Title style={{ color: 'red' }} class="h1">
              Welcome to profile section
            </Card.Title>
            <Card.Text>View your profile here </Card.Text>
          </Card.Body>
        </div>
        <MDBRow>
          <MDBCol sm="2"></MDBCol>
          <MDBCol sm="2">
            {/* <MDBCard > */}
            <div className=" card aligns-items-center justify-content-center  text-center mx-auto ">
              <div className="card-body">
                <img
                  src={Chief}
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
                    <p class="mb-0">User Name</p>
                  </div>
                  <div class="col-sm-9">
                    <p class="text-muted mb-0">{(user.userName)}</p>
                  </div>
                </div>
                <hr />
                <div class="row">
                  <div class="col-sm-3">
                    <p class="mb-0">Email</p>
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
                    <p class="text-muted mb-0">{(user.roles)}</p>
                  </div>
                </div>
                <hr />
              </div>
            </div>
          </MDBCol>
          <MDBCol sm="2"></MDBCol>
        </MDBRow>
        <MDBRow>
          <MDBCol>
            {/* <MDBCard > */}
            <MDBCardBody>
              <div className="mx-auto text-center">
                <MDBCardTitle>View All Users</MDBCardTitle>
                <MDBBtn
                  color="danger"
                  onClick={() => fetchAllUsers()}
                >
                  Click Here
                </MDBBtn><MDBCardBody></MDBCardBody>
              </div>
              <MDBTable>
      <MDBTableHead>
        <tr>
          <th scope='col'>#</th>
          <th scope="col">User Name</th>
          
          <th scope="col">Email</th>
          <th scope="col">Roles</th>
          <th scope="col">Vehicles</th>
        </tr>
      </MDBTableHead>
      <MDBTableBody>
        {allUser.map((item)=>(
        <tr>
          <th scope="row">{i++}</th>
          <td>{item.userName}</td>
          <td>{item.email}</td>
          <td>{item.roles}</td>
          <td>{item.vehicle.map((items)=>(items.plateNumber+','))}</td>
        </tr>))}
       
      </MDBTableBody>
    </MDBTable>
            </MDBCardBody>
            {/* </MDBCard> */}
          </MDBCol>
          </MDBRow>
      </Card>
    </Helmet>
  );
}
