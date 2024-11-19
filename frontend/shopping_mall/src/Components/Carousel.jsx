// Carousel.js
import React, { useEffect, useRef } from "react";
import Slider from "react-slick";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";

const carouselData = [
  {
    brandProduct:
      "https://cmsimages.shoppersstop.com/GD_7beff2e100/GD_7beff2e100.png",
    brandLogo:
      "https://cmsimages.shoppersstop.com/globaldesi_web_86ba5806ed/globaldesi_web_86ba5806ed.png",
    discount: "UP TO 75% OFF",
  },
  {
    brandProduct:
      "https://cmsimages.shoppersstop.com/aurelia_33053671b4/aurelia_33053671b4.png",
    brandLogo:
      "https://cmsimages.shoppersstop.com/Aurelia_web_e1fc622d26/Aurelia_web_e1fc622d26.png",
    discount: "UP TO 50% OFF",
  },
  {
    brandProduct:
      "https://cmsimages.shoppersstop.com/Indya_67041bf873/Indya_67041bf873.png",
    brandLogo:
      "https://cmsimages.shoppersstop.com/Indya_web_3121023fc3/Indya_web_3121023fc3.png",
    discount: "FLAT 15% OFF",
  },
  // Add more items as needed
];

const Carousel = () => {
  const sliderRef = useRef(null);

  const settings = {
    dots: false,
    infinite: true,
    centerMode: true,
    slidesToShow: 3,
    // focusOnSelect: true,
    centerPadding: "0",
    speed: 500,
  };

  useEffect(() => {
    const interval = setInterval(() => {
      if (sliderRef.current) {
        sliderRef.current.slickNext(); // Move to the next slide
      }
    }, 3000); // Change slide every 3 seconds

    return () => clearInterval(interval); // Clear interval on component unmount
  }, []);

  return (
    <div className="text-center bg-black text-white py-8">
      <h2 className="text-2xl lg:text-4xl font-medium mb-4">
        <span style={{ color: "#ffffff" }}>Vibrant</span>{" "}
        <span style={{ color: "#A3A3A3" }}>Elegance</span>
      </h2>
      <Slider ref={sliderRef} {...settings} className="max-w-screen-lg mx-auto">
        {carouselData.map((item, index) => (
          <div key={index} className="px-4"> {/* Adjust padding here */}
            <div className="relative h-[500px] bg-gradient-to-b from-gray-900 to-transparent rounded-lg overflow-hidden transition duration-500">
              <img
                src={item.brandProduct}
                alt="brand product"
                className="object-cover w-full h-full"
              />
              <div className="absolute inset-0 flex flex-col justify-end items-center p-4">
                <img
                  src={item.brandLogo}
                  alt="brand logo"
                  className="mb-4 w-24 h-10"
                />
                <p className="text-2xl font-bold">{item.discount}</p>
              </div>
            </div>
          </div>
        ))}
      </Slider>

    </div>
  );
};

// Styles for navigation buttons
const buttonContainerStyle = {
  display: 'flex',
  justifyContent: 'space-between',
  position: 'absolute',
  top: '50%',
  width: '100%',
  transform: 'translateY(-50%)',
  zIndex: 1,
};

const buttonStyle = {
  fontSize: '32px', // Increased font size
  color: 'white',
  padding: '15px', // Increased padding
  borderRadius: '50%', // Keep rounded shape
  backgroundColor: 'rgba(0, 0, 0, 0.5)',
  border: 'none',
  transition: 'background-color 0.3s',
  width: '50px', // Set width for the button
  height: '50px', // Set height for the button
  display: 'flex', // Use flex to center the content
  alignItems: 'center',
  justifyContent: 'center',
};

export default Carousel;
