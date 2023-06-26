import React,{useState} from 'react';
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
  MDBRow,MDBContainer,MDBInput,
} from 'mdb-react-ui-kit';
import { ToastContainer, toast } from "react-toastify";
import axios from "axios";


export default function AddVehicle(){
  const [make,setMake]=useState('');
  const [model,setModel]=useState('');
  const [year,setYear]=useState('');
  const [plateNumber,setPlateNumber]=useState('');
  const token = localStorage.getItem("token");
  const userId=localStorage.getItem("userId");
  const handleSubmit= async(event) => {
    event.preventDefault();
     
      try{
      // Send the form data to the backend API
      axios.post(`http://localhost:9096/vehicles/${userId}`, {
        make,
        model,
        year,
        plateNumber
     
      },{
  
      headers: {
        Authorization: `Bearer ${token}`,
      },
    }).then(response=>{
      console.log(response);
      if (response.status === 200) {
        // Display success toast
        toast.success("Vehicle added successfully!");
        // Reset the form fields
        setMake("");
        setModel("");
        setYear("");
        setPlateNumber("");
        
        } else {
        // Display error toast
        toast.error("Failed to add vehicle. Please try again.");
        }
        
    })
    
   } catch (error) {
      // Display error toast
      toast.error("Failed to add vehicle. Please try again.");
      console.error(error);
      }

    };

  return(
    <><ToastContainer/>
    <MDBRow>
    <MDBCol sm='2'> </MDBCol>

      <MDBCol>
        <MDBContainer className=" d-flex flex-column w-500">
    
        
            <MDBInput
              wrapperClass="mb-4"
              label="Make*"
              id="form1"
              type="text"
              value={make}
              onChange={(e) => setMake(e.target.value)}
              required="required"
            />
            <MDBInput wrapperClass="mb-4" label="Model*" id="form2" type="text" value={model}
              onChange={(e) => setModel(e.target.value)}
              required="required"/>
            <MDBInput
              wrapperClass="mb-4"
              label="Year*"
              id="form3"
              type="number"
              value={year}
              onChange={(e) => setYear(e.target.value)}
              required="required"
            />
            <MDBInput
              wrapperClass="mb-4"
              label="Plate Number*"
              id="form4"
              type="text"
              value={plateNumber}
              onChange={(e) => setPlateNumber(e.target.value)}
              required="required"
            />
            <MDBRow>
            <MDBBtn className="mb-4 w-100" color="danger" onClick={handleSubmit}>
              ADD
            </MDBBtn>
            
            </MDBRow>
    
          
      </MDBContainer>
      </MDBCol>
     <MDBCol sm='2'> </MDBCol>
      </MDBRow>
      </>
      );
    }
