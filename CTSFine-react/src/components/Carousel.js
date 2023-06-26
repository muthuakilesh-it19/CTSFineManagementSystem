import React from 'react';
import { MDBCarousel, MDBCarouselItem } from 'mdb-react-ui-kit';
import Park from '../assets/Images/Park.png';
import parking1 from '../assets/Images/parking1.png';
import parking from '../assets/Images/parking.png';
export default function Carousel() {
  return (
    <MDBCarousel showIndicators showControls fade className="w-100 ">
      <MDBCarouselItem
        className="w-100 d-block"
        itemId={1}
        src={parking}
        alt="..."
      >
     
      </MDBCarouselItem>

      <MDBCarouselItem
        className="w-100 d-block"
        itemId={2}
        src={parking1}
        alt="..."
      >
       
      </MDBCarouselItem>

      <MDBCarouselItem
        className="w-100 d-block"
        itemId={3}
        src={Park}
        alt="..."
      >
       
      </MDBCarouselItem>
    </MDBCarousel>
  );
}
