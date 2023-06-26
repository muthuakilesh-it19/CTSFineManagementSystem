import React,{useState,useEffect} from 'react';
import {
  MDBBadge,
  MDBBtn,
  MDBTable,
  MDBTableHead,
  MDBTableBody,
} from 'mdb-react-ui-kit';
import Card from 'react-bootstrap/Card';
import axios from "axios";

export default function ViolationTable() {
  const [users, setUsers] = useState([]);
  const token = localStorage.getItem("token");
  const userId=localStorage.getItem("userId");
  useEffect(() => {

    // Make the HTTP GET request to fetch the user data

    // Replace `/api/users` with the actual backend API endpoint to retrieve the user data

    axios

      .get(`http://localhost:9091/violation/user/${userId}`,{
        headers:{
          Authorization: `Bearer ${token}`,
            },
      })

      .then((response) => {

        // Update the state variable with the fetched user data

        setUsers(response.data);
        console.log(response.data);

      })

      .catch((error) => {

        console.log(error);

      });

  }, []);
  return (
    <Card display='block'>
    <MDBTable  align="middle">
      <MDBTableHead>
        <tr>
          <th scope="col">Violation Type</th>
          <th scope="col">Date</th>
          <th scope="col">Location</th>
          
          <th scope="col">Plate Number</th>
          
        </tr>
      </MDBTableHead>
      {users.map((user) => (
      <MDBTableBody>
        <tr key={user.userId}>
          <td>
            <div className="d-flex align-items-center">
              
              <div className="ms-3">
                <p className="fw-bold mb-1">{user.violationType}</p>
                {/* <p className="text-muted mb-0">john.doe@gmail.com</p> */}
              </div>
            </div>
          </td>
          <td>
            <p className="fw-normal mb-1">{user.date}</p>
            {/* <p className="text-muted mb-0">IT department</p> */}
          </td>
          <td>
            <p className="fw-normal mb-1">{user.location}</p>
            {/* <p className="text-muted mb-0">IT department</p> */}
          </td>
          
          <td>{user.plateNumber}</td>
          
        </tr>
        
          
      </MDBTableBody>
))}
    </MDBTable>
    </Card>
  );
}
