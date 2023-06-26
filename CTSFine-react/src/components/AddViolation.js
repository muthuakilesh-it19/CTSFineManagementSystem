import React, { useState } from 'react';
import {
  MDBContainer,
  MDBTabs,
  MDBTabsItem,
  MDBTabsLink,
  MDBTabsContent,
  MDBTabsPane,
  MDBBtn,
  MDBIcon,
  MDBInput,
  MDBCheckbox,
} from 'mdb-react-ui-kit';
import axios from "axios";

import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

export default function AddViolation() {
  const [justifyActive, setJustifyActive] = useState('tab1');

  const handleJustifyClick = (value) => {
    if (value === justifyActive) {
      return;
    }

    setJustifyActive(value);
  };

  const [violationType, setViolationType] = useState("");
  const [date, setDate] = useState("");
  const [location, setLocation] = useState("");
  const [plateNumber, setPlateNumber] = useState("");


  let [violationTypeError, setViolationTypeError] = useState(false);
  let [dateError, setDateError] = useState(false);
  let [locationError, setLocationError] = useState(false);
  let [plateNumberError, setPlateNumberError] = useState(false);

  const token = localStorage.getItem("token");

  const handle = () => {
    console.log("clicked.....")
  }
  const handleClick = async (e) => {
    e.preventDefault();
    if (violationType=== "") {
      setViolationTypeError(true);
      return;
      } else setViolationTypeError(false);
      if (date === "") {
        setDateError(true);
      return;
      } else setDateError(false);
      if (location === "") {
        setLocationError(true);
      return;
      } else setLocationError(false);
      if (plateNumber === "") {
        setPlateNumberError(true);
      return;
      } else setPlateNumberError(false);
        
      try {
      // Send the form data to the backend API
      const response = await axios.post("http://localhost:9091/violation", {
        violationType,
        date,
        location,
        plateNumber
        
     
      },{
  
      headers: {
        Authorization: `Bearer ${token}`,
      },
    }).then(response=>{
      console.log(response.status);
      if(response.status === 201){
      // Display success toast
      toast.success("Violation added successfully!");
      // Reset the form fields
      setViolationType("");
      setDate("");
      setLocation("");
      setPlateNumber("");
      
      } else {
      // Display error toast
      toast.error("Failed to add violation. Please try again.");
      }})
      } catch (error) {
      // Display error toast
      toast.error("Failed to add violation. Please try again.");
      console.error(error);
      }
      };

      const [description, setDescription] = useState("");
      const [amount, setAmount] = useState("");
      const [userName, setUserId] = useState("");
      const [plateNumber1, setVehicleId] = useState("");
      const [dateFine, setDateFine] = useState("");
      let [descriptionError, setDescriptionError] = useState(false);
      let [amountError, setAmountError] = useState(false);
      let [userIdError, setUserIdError] = useState(false);
      let [vehicleIdError, setVehicleIdError] = useState(false);

      const handleClickFine = async (e) => {
        e.preventDefault();
        if (description=== "") {
          setDescriptionError(true);
          return;
          } else setDescriptionError(false);
          if (amount === "") {
            setAmountError(true);
          return;
          } else setAmountError(false);
          if (userName === "") {
            setUserIdError(true);
          return;
          } else setUserIdError(false);
          if (plateNumber1 === "") {
            setVehicleIdError(true);
          return;
          } else setVehicleIdError(false);
            console.log({date,amount,userName,plateNumber1});
          try {
          // Send the form data to the backend API
           await axios.post("http://localhost:9097/fines", {
            description,
            amount,
            userName,
            plateNumber1,
            dateFine
            
         
          })
        // Handle the response
        .then(response=>{
          console.log(response.status);
          if (response.status == 201) {
          // Display success toast
          toast.success("Fine added successfully!");
          // Reset the form fields
          setDescription("");
          setAmount("");
          setUserId("");
          setVehicleId("");
          
          } else {
          // Display error toast
          toast.error("Failed to add Fine. Please try again.");
          }})
          } catch (error) {
          // Display error toast
          toast.error("Failed to add Fine. Please try again.");
          console.error(error);
          }
          };
  return (
    <>
    <ToastContainer/>
    <MDBContainer className=" d-flex flex-column w-500">
      <MDBTabs
        pills
        justify
        className="mb-3 d-flex flex-row justify-content-between"
      >
        <MDBTabsItem>
          <MDBTabsLink
            onClick={() => handleJustifyClick('tab1')}
            active={justifyActive === 'tab1'}
          >
            Add Violation
          </MDBTabsLink>
        </MDBTabsItem>
        <MDBTabsItem>
          <MDBTabsLink
            onClick={() => handleJustifyClick('tab2')}
            active={justifyActive === 'tab2'}
          >
            Add Fine
          </MDBTabsLink>
        </MDBTabsItem>
      </MDBTabs>

      <MDBTabsContent>
        <MDBTabsPane show={justifyActive === 'tab1'}>
          <MDBInput
            wrapperClass="mb-4"
            label="Violation Type*"
            id="form1"
            type="text" 
            value={violationType}
            error={violationTypeError}
            onChange={(e) => setViolationType(e.target.value)}
            required="required"
          />
          <MDBInput wrapperClass="mb-4" label="Date*" id="form2" type="date" value={date}
            error={dateError}
            onChange={(e) => setDate(e.target.value)}
            required="required"/>
          <MDBInput
            wrapperClass="mb-4"
            label="location*"
            id="form3"
            type="location"
            value={location}
            error={locationError}
            onChange={(e) => setLocation(e.target.value)}
            required="required"
          />
          <MDBInput
            wrapperClass="mb-4"
            label="Plate Number*"
            id="form4"
            type="text"
            value={plateNumber}
            error={plateNumberError}
            onChange={(e) => setPlateNumber(e.target.value)}
            required="required"          />

          <MDBBtn className="mb-4 w-100" color="danger" onClick={handleClick}>
            ADD
          </MDBBtn>
        </MDBTabsPane>

        <MDBTabsPane show={justifyActive === 'tab2'}>
          <MDBInput
            wrapperClass="mb-4"
            label="Description*"
            id="form1"
            type="text"
            value={description}
            error={descriptionError}
            onChange={(e) => setDescription(e.target.value)}
            required="required"
          />

          <MDBInput wrapperClass="mb-4" label="Amount*" id="form2" type="cost" value={amount}
            error={amountError}
            onChange={(e) => setAmount(e.target.value)}
            required="required" />
            <MDBInput wrapperClass="mb-4" label="Date*" id="form5" type="date" value={dateFine}
            error={dateError}
            onChange={(e) => setDateFine(e.target.value)}
            required="required"/>
          <MDBInput
            wrapperClass="mb-4"
            label="User Name*"
            id="form3"
            type="text"
            value={userName}
            error={userIdError}
            onChange={(e) => setUserId(e.target.value)}
            required="required"
          />
          <MDBInput
            wrapperClass="mb-4"
            label="plateNumber*"
            id="form4"
            type="text"
            value={plateNumber1}
            error={vehicleIdError}
            onChange={(e) => setVehicleId(e.target.value)}
            required="required"
          />

          <MDBBtn className="mb-4 w-100" color="danger" onClick={handleClickFine}>
            ADD
          </MDBBtn>
          
        </MDBTabsPane>
      </MDBTabsContent>
    </MDBContainer>
    </>
  );
}
