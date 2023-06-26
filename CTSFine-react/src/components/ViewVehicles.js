import React,{useEffect,useState} from 'react';
import { MDBTable, MDBTableHead, MDBTableBody, MDBCol ,MDBCard,MDBRow} from 'mdb-react-ui-kit';
import axios from "axios";


export default function ViewVehicles() {

  const [carData, setCarData] = useState([]);
  const userId=localStorage.getItem("userId");
  const token = localStorage.getItem("token");

  useEffect(() => {
    fetchCarData();
  }, []);

  const fetchCarData = async () => {
    try {
      const response = await axios.get(`http://localhost:9096/vehicles/${userId}`);
      setCarData(response.data);
      // console.log(response.data);
      // console.log(carData);
      
    } catch (error) {
      console.error(error);
    }
  };
let i=1;
  return (
    <div  className='justify-content-center align-items-center'>
      <MDBRow>
        <MDBCol sm='2'></MDBCol>
        <MDBCol>
    <MDBCard>
      
        
    <MDBTable>
      <MDBTableHead>
        <tr>
          <th scope='col'>#</th>
          <th scope="col">Make</th>
          <th scope="col">Model</th>
          <th scope="col">Year</th>
          <th scope="col">Plate Number</th>
        </tr>
      </MDBTableHead>
      <MDBTableBody>
        {carData.map((item)=>(
        <tr>
          <th scope="row">{i++}</th>
          <td>{item.make}</td>
          <td>{item.model}</td>
          <td>{item.year}</td>
          <td>{item.plateNumber}</td>
        </tr>))}
       
      </MDBTableBody>
    </MDBTable>
    </MDBCard>
    </MDBCol>
    <MDBCol sm='2'></MDBCol>

    </MDBRow>
    
    </div>
  );
}
