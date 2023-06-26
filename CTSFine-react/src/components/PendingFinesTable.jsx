import React, { useState, useEffect } from 'react';
import {
  MDBBadge,
  MDBBtn,
  MDBTable,
  MDBTableHead,
  MDBTableBody,
} from 'mdb-react-ui-kit';
import Card from 'react-bootstrap/Card';
import axios from "axios";

export default function PendingFinesTable() {

  const [users, setUsers] = useState([]);

  const token = localStorage.getItem("token");
  const userId = localStorage.getItem("userId");

  useEffect(() => {
    // Make the HTTP GET request to fetch the user data
    // Replace `/api/users` with the actual backend API endpoint to retrieve the user data
    axios.get(`http://localhost:9097/fines/user/${userId}`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((response) => {
        // Update the state variable with the fetched user data
        setUsers(response.data);
        console.log(response.data);

      }).catch((error) => {
        console.log(error);
      });

  }, []);

  const handlePay = (user) => {
    const payData = {
      "id": user.id,
      "amount": user.amount,
      "description": user.description,
      "paid": true,
      
      
    }
    console.log(payData);
    try {

      const response = axios.put(`http://localhost:9097/fines/${user.id}`, payData)

        window.location.reload(false);
    }
    catch(err) { 
      console.log(err);
    }
  }
  return (
    <Card display='block'>
      <MDBTable align="middle">
        <MDBTableHead>
          <tr>
            <th scope="col">Fine Description</th>
            <th scope="col">Fine Amount</th>
            <th scope='col'>Penalty</th>
            <th scope="col">Status</th>
            <th scope='col'>Payment</th>


          </tr>
        </MDBTableHead>
        {users.map((user) => (
          <MDBTableBody>
            <tr key={user.id}>
              <td>
                <div className="d-flex align-items-center">

                  <div >
                    <p >{user.description}</p>
                    {/* <p className="text-muted mb-0">john.doe@gmail.com</p> */}
                  </div>
                </div>
              </td>
              <td>
                <p >{user.amount}</p>
                {/* <p className="text-muted mb-0">IT department</p> */}
              </td>
              <td>
                <p>{user.penalty}</p>
              </td>
              <td>
                {user.paid ?
                  <MDBBadge color="success" pill>
                    Paid
                  </MDBBadge> :
                  <MDBBadge color="danger" pill>
                    Not paid
                  </MDBBadge>
                }
              </td>
              <td>
                <MDBBtn className="mb-4 w-50%" color="danger" onClick={() => handlePay(user)}>Pay</MDBBtn>
              </td>


            </tr>


          </MDBTableBody>
        ))}
      </MDBTable>
    </Card>
  );
}
