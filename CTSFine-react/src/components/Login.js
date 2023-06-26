import React, { useState,useRef } from 'react';
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
import '../styles/style.css';
import {Form} from 'react-bootstrap';
import axios from 'axios';
import { ToastContainer, toast } from "react-toastify";

import { useNavigate, Link } from 'react-router-dom';

const Login=({tab})=> {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const navigate = useNavigate();
  
  const handleSubmit = async (e) => {
    e.preventDefault();
    let values = { "email": email, "password": password }
    // let values = JSON.stringify(data)
    console.log(values)

    const validate = () => {

      let temp = {};
      console.log(/\S+@\S+\.\S+/.test(values.email));
      temp.email = /\S+@\S+\.\S+/.test(values.email)

        ? ''

        : 'Email is not valid.';

      temp.password = values.password !== '' ? '' : 'This field is required.';

      console.log(temp);

      return Object.values(temp).every((x) => x === '');

    };

    if (validate()) {
      axios
      .post('http://localhost:9096/user/authenticate', values)
      .then((res) => {
        console.log(res.data)
        const { token, username,role,userId } = res.data;


        localStorage.setItem('token', token);

        //localStorage.setItem('expirationMinutes', expirationMinutes);

        //localStorage.setItem('roleId', role);

        localStorage.setItem('userName', username);
        localStorage.setItem('userId',userId);



        // Redirect based on role ID

        if (role === 2) {

          navigate('/userdashboard');

        } else if (role === 1) {

          navigate('/officerdashboard');

        } else {

          // Handle unknown role ID

          navigate('/home'); // Redirect to login page

        }

      })


        .catch((err) => console.log('Login failed', err));

    }

  };


  const[userName,setName]=useState('');
  const[emailRegister,setEmailRegister]=useState('');
  const[passwordRegister,setPasswordRegister]=useState('');

  const handleRegister =async(e) =>{
    e.preventDefault();
      console.log(userName,emailRegister,passwordRegister);
      const validate1 = () => {

        let temp = {};
        console.log(/\S+@\S+\.\S+/.test(email));
        temp.email = /\S+@\S+\.\S+/.test(emailRegister)
  
          ? ''
  
          : 'Email is not valid.';
          temp.userName = userName !== '' ? '' : 'This field is required.';
        temp.passwordRegister = passwordRegister !== '' ? '' : 'This field is required.';
  
        console.log(temp);
  
        return Object.values(temp).every((x) => x === '');
  
      };
      if(validate1()){
    try{
      const response = await axios.post('http://localhost:9096/user/register',{
        userName,
        emailRegister,
        passwordRegister,
        
      
      }).then(response=>{
      console.log(response.status);
      if(response.status === 200){
        // Display success toast
        toast.success("Registration successfull!");
        // Reset the form fields
        setName('');
      setEmailRegister('');
      setPasswordRegister('');
        
        } else {
        // Display error toast
        toast.error("Failed to Register. Please try again.");
        }
      
      
    })}
    catch(error){
      console.error(error);
    }
  };
}
  const [validated, setValidated] = useState(false);

  const handleSubmitReg = (event) => {
    const form = event.currentTarget;
    if (form.checkValidity() === false) {
      event.preventDefault();
      event.stopPropagation();
    }

    setValidated(true);
  };

  const [justifyActive, setJustifyActive] = useState('tab1');
  const titleRef = useRef();
  const handleJustifyClick = (value) => {
    if (value === justifyActive) {
      return;
    }

    setJustifyActive(value);
  };

  return (
  
    <MDBContainer className="p-5 my-5 d-flex flex-column w-500" ref={titleRef}>
      <ToastContainer/>
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
            Login
          </MDBTabsLink>
        </MDBTabsItem>
        <MDBTabsItem>
          <MDBTabsLink
            onClick={() => handleJustifyClick('tab2')}
            active={justifyActive === 'tab2'}
          >
            Register
          </MDBTabsLink>
        </MDBTabsItem>
      </MDBTabs>

      <MDBTabsContent>
        <MDBTabsPane show={justifyActive === 'tab1'}>
          
        <Form onSubmit={handleSubmit}>
          <MDBInput
          required
            wrapperClass="mb-4"
            label="Email address"
            id="form1"
            type="email"
            onChange={(e)=>setEmail(e.target.value)}
            controlId="exampleForm.ControlInput1"         
          />
          <MDBInput
          required
            wrapperClass="mb-4"
            label="Password"
            id="form2"
            type="password"
            onChange={(e)=>setPassword(e.target.value)}
            controlId="formPlaintextPassword"
          />

          <div className="d-flex justify-content-between mx-4 mb-4">
            <MDBCheckbox
              name="flexCheck"
              value=""
              id="flexCheckDefault"
              label="Remember me"
            />
            <a href="!#">Forgot password?</a>
          </div>

          <MDBBtn className="mb-4 w-100" color='danger' type='submit' >Sign in</MDBBtn>
          </Form>
          <p className="text-center">
            Not a member? <a href="#!">Register</a>
          </p>
        </MDBTabsPane>
        
        <MDBTabsPane show={justifyActive === 'tab2'}>
          
<Form noValidate validated={validated} onSubmit={handleSubmitReg}>
          <MDBInput required="required" wrapperClass="mb-4" label="Name" id="form1" type="text" controlId="formGroupUserName" value={userName} onChange={(e) => setName(e.target.value)}  />
          
          <MDBInput required="required" wrapperClass="mb-4" label="Email" id="form1" type="email" controlId="formGroupEmail" value={emailRegister} onChange={(e) => setEmailRegister(e.target.value)} />
          <MDBInput
          required="required"
            wrapperClass="mb-4"
            label="Password"
            id="form1"
            type="password"
            controlId="formGroupPassword"
            value={passwordRegister} onChange={(e) => setPasswordRegister(e.target.value)}  
          />

          <div className="d-flex justify-content-center mb-4">
            <MDBCheckbox
              name="flexCheck"
              id="flexCheckDefault"
              label="I have read and agree to the terms"
            />
          </div>

          <MDBBtn className="mb-4 w-100" color='danger' onClick={handleRegister}>Sign up</MDBBtn>
          </Form>
        </MDBTabsPane>
      </MDBTabsContent>
    </MDBContainer>
  );
}

export default Login;
