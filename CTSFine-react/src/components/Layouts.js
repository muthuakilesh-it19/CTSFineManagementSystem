import React,{Fragment} from 'react';
import Headers from './Header';
import Routers from './Routers';
import Footer from './Footer';
import Card from 'react-bootstrap/Card';

export default function Layouts() {
  
  return (
    <>
    
      
      <Fragment>
      <div>
        <Routers />
      </div>
      </Fragment>
      <Footer />
    </>
  );
}
