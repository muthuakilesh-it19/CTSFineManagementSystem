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
  import axios from "axios";
  import { ToastContainer, toast } from "react-toastify";


export default function (){
    const [make,setMake]=useState('');
    const [model,setModel]=useState('');
    const [year,setYear]=useState('');
    const [plateNumberChange,setPlateNumberChange]=useState('');
    const [plateNumber,setPlateNumber]=useState('');

    const token = localStorage.getItem("token");
    const userId=localStorage.getItem("userId");

    const handleFormSubmit = async (e) => {
      e.preventDefault();
      try{
        console.log(make,model,year,plateNumber);
        // Send the form data to the backend API
        axios.put(`http://localhost:9096/vehicles/${userId}/update/${plateNumber}`, {
          make,
          model,
          year,
          plateNumber
       
        },{
    
        headers: {
          Authorization: `Bearer ${token}`,
        },
      }).then(response=>{
        console.log(response.status);
        if (response.status === 200) {
          // Display success toast
          toast.success("Vehicle updated successfully!");
          // Reset the form fields
          setMake("");
          setModel("");
          setYear("");
          setPlateNumber("");
          
          } else {
          // Display error toast
          toast.error("Failed to update vehicle. Please try again.");
          }
          
      });
    } catch (error) {
      // Display error toast
      toast.error("Failed to update vehicle. Please try again.");
      console.error(error);
      }
    }
const [carData, setCarData] = useState([]);

    const fetchCarData = async () => {
        
        try {
          const response = await axios.get(`http://localhost:9096/vehicles/${userId}/plate/${plateNumberChange}`);
          setCarData(response.data);
          console.log(response.status);
          if(response.status===200){
          setMake(carData.make);
          setModel(carData.model);
          setYear(carData.year);
          setPlateNumber(carData.plateNumber);
          }
          
        } catch (error) {
          console.error(error);
          setMake('');
            setModel('');
            setYear('');
        }
      };

      const deleteData = async () => {
        
        try {
          const response = await axios.delete(`http://localhost:9096/vehicles/${userId}/${plateNumberChange}`);
          
          console.log(response.status);
          
          if (response.status === 200) {
            // Display success toast
            toast.success("Vehicle deleted successfully!");
            // Reset the form fields
            setMake("");
            setModel("");
            setYear("");
            setPlateNumber("");
        } 
      }catch (error) {
          console.error(error);
          
        }
      };



return(

    <><ToastContainer/>
    <MDBRow>
        <MDBCol>
     <MDBInput
      wrapperClass="mb-4"
      label="Plate Number"
      id="form4"
      type="text"
      value={plateNumberChange}
      onChange={(e) => setPlateNumberChange(e.target.value)}
      required="required"
    />
    <MDBBtn className="mb-4 w-50" color="primary" onClick={fetchCarData}>
              Search
            </MDBBtn>
            <MDBBtn className="mb-4 w-50" color="danger" onClick={deleteData}>
              Delete
            </MDBBtn>
            </MDBCol>
            <MDBCol>
<MDBContainer className=" d-flex flex-column w-500">
    
        
    <MDBInput
      wrapperClass="mb-4"
      label="Make"
      id="form1"
      type="text"
      value={make}
      onChange={(e) => setMake(e.target.value)}
      required
    />
    <MDBInput wrapperClass="mb-4" label="Model" id="form2" type="text" value={model}
      onChange={(e) => setModel(e.target.value)}
      required/>
    <MDBInput
      wrapperClass="mb-4"
      label="Year"
      id="form3"
      type="number"
      value={year}
      onChange={(e) => setYear(e.target.value)}
      required
    />
    <MDBInput
      wrapperClass="mb-4"
      label="PlateNumber"
      id="form5"
      type="text"
      value={plateNumber}
      onChange={(e) => setYear(e.target.value)}
      required
    />
   
    <MDBBtn className="mb-4 w-50" color="primary" onClick={handleFormSubmit}>
              Update
            </MDBBtn>
    </MDBContainer>
    </MDBCol>
    </MDBRow>
    </>
);

}