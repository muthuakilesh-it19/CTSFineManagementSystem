import React from 'react';
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
  MDBRow,
} from 'mdb-react-ui-kit';
import Image from 'react-bootstrap/Image';
import Tesla from '../assets/Images/Tesla.jpeg';
import TextTransition, { presets } from 'react-text-transition';
function FluidImage() {
  const TEXTS = ['Fine Issuance:Easily generate fines and assign them to vehicles or users with just a few clicks.',' Vehicle and User Management:Maintain a database of vehicles and users, enabling seamless association of fines with the respective entities.',  'User-Friendly Interface:Enjoy a user-friendly interface that is intuitive and easy to navigate, ensuring a smooth user experience.', 'Fine Tracking: Keep track of fines and their status, including pending fines, paid fines, and overdue fines.','Reporting and Analytics: Generate comprehensive reports and gain valuable insights into fine statistics, payment trends, and more.'];
  const [index, setIndex] = React.useState(0);

  React.useEffect(() => {
    const intervalId = setInterval(
      () => setIndex((index) => index + 1),
      3000, // every 3 seconds
    );
    return () => clearTimeout(intervalId);
  }, []);
  return (
  
    
      <>
  <Image src={Tesla} fluid />
  <div className='mask' style={{ backgroundColor: 'rgba(0, 0, 0, 0.6)' }}>
        <div className='d-flex justify-content-center align-items-center h-100'>
          <div className='text-white mb-0'>
         <TextTransition springConfig={presets.wobbly}>{TEXTS[index % TEXTS.length]}<hr/></TextTransition>
         </div>
        </div>
        </div>
        </>
  
  );
}

export default FluidImage;