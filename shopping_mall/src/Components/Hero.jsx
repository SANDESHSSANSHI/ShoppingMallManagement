import React from "react";
import Slider from "react-slick"; // Importing the Slider component from react-slick
import "slick-carousel/slick/slick.css"; // Importing slick CSS
import "slick-carousel/slick/slick-theme.css"; // Importing slick theme CSS
import { getuser } from '../Functions/userdata'; // Ensure correct path to userdata
import { useNavigate } from 'react-router-dom';


// Data for the hero banner images and links
const heroBannerData = [
  {
    imgSrc: "https://cmsimages.shoppersstop.com/Floral_Bedsheet_web_a309c84057/Floral_Bedsheet_web_a309c84057.png",
    link: "/search/result?q=Floral%20Bedsheets",
  },
  {
    imgSrc: "https://cmsimages.shoppersstop.com/Floral_Curtain_web_44e3b5d3e0/Floral_Curtain_web_44e3b5d3e0.png",
    link: "/search/result?q=floral+curtains&filter=categoryPath2_uFilter%253A%2522Home%2520Furnishing%2522&page=2",
  },
  {
    imgSrc: "https://cmsimages.shoppersstop.com/Rugs_web_fc889424dc/Rugs_web_fc889424dc.png",
    link: "/search/result?q=printed%20rugs",
  },
  {
    imgSrc: "https://cmsimages.shoppersstop.com/Golden_Cushions_web_574b42732f/Golden_Cushions_web_574b42732f.png",
    link: "/search/result?q=gold%20cushion%20covers",
  },
];

// Hero component definition
const Hero = () => {
  // Settings for the carousel slider
  const settings = {
    dots: true, // Show navigation dots
    infinite: true, // Loop the slides
    speed: 500, // Transition speed
    autoplay: true, // Enable autoplay
    autoplaySpeed: 3000, // Speed of autoplay
    slidesToShow: 1, // Number of slides to show at once
    slidesToScroll: 1, // Number of slides to scroll at once
    arrows: false, // Hide arrow navigation
  };
  const navigate = useNavigate(); 

  const { isLoggedIn, type } = getuser(); // Fetch user details

  // Function to handle navigation based on user type
  const handleNavigation = (event) => {
    // Prevent navigation if clicking on certain elements


    if (isLoggedIn) {
      // Navigate based on user type
      if (type === 'Customer') navigate('/Mall/Customer');
      if (type === 'MallAdmin') navigate('/Mall/MallAdmin');
      if (type === 'ShopOwner') navigate('/Mall/ShopOwner');
    } else {
      navigate('/login'); // Redirect to login if not logged in
    }
  };
  return (
    <div className="bg-transparent mx-4 md:mx-[70px]" data-testid="herobanner" onClick={handleNavigation}>
      <div className="text-left text-base font-medium capitalize leading-xs md:text-center md:text-xl lg:text-2xl xl:text-[2rem] mb-5 md:mb-8 select-none md:select-text">
        {/* <strong>
          <span className="text-gray-400">Get Your</span>
          <span className="text-gray-900"> Home Festive-Ready!</span>
          <br />
          <span className="text-black">Extra ₹1000 Off* | Use Code : FESTIVE24</span>
        </strong> */}
      </div>
      <div className="bg-transparent carousel" data-testid="carousel">
        <Slider {...settings}>
          {heroBannerData.map((item, index) => (
            <div key={index} className="bg-transparent cursor-pointer">
              <a target="_self" href={item.link}>
                <img
                  alt="hero_banner"
                  src={item.imgSrc}
                  className="w-full h-auto transition-transform transform hover:scale-105"
                  loading="eager"
                />
              </a>
            </div>
          ))}
        </Slider>
      </div>
    </div>
  );
};

export default Hero;
