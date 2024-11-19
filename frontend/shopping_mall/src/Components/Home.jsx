import React, { useState } from 'react'; 
import { useNavigate } from 'react-router-dom';
import '../styles/App.css';
import image from '../pics/imgage1.png';
import filmimg from '../pics/film.png';
import m1 from '../pics/m1.png';
import m2 from '../pics/m2.png';
import m3 from '../pics/m3.png';
import m4 from '../pics/m4.png';
import Carousel from './Carousel';
import Hero from './Hero';
import Review from './Review';
import Slides from './Slides';
import { getuser } from '../Functions/userdata'; // Ensure correct path to userdata

const Home = () => {
  const navigate = useNavigate(); 
  const [activeSection, setActiveSection] = useState('home'); // Track the current section

  const { isLoggedIn, id, type } = getuser(); // Fetch user details

  // Function to handle navigation based on user type
  const handleNavigation = (event) => {
    // Prevent navigation if clicking on certain elements
    if (event.target.closest('.no-nav')) return;

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
    <div className="App-header bg-white" onClick={handleNavigation}>
      <div className="App w-[100vw]">
        <div className="bg-blue">
          <Hero />
        </div>

        {/* Movies Section */}
        <div className="w-full bg-white min-h py-10">
          <h1 className="text-4xl font-bold text-center mb-8 text-black">Movies that Are on the Screen Today</h1>
          <div className="grid gap-6 lg:grid-cols-4 md:grid-cols-2 grid-cols-1 px-5">
            {[ 
              { image: m1, title: "Ice Bergs", showtimes: "17:00, 19:50, 23:05" },
              { image: m2, title: "Pumpkin Drop", showtimes: "17:00, 19:50, 23:05" },
              { image: m3, title: "Coffee of the day", showtimes: "17:00, 19:50, 23:05" },
              { image: m4, title: "Beside River", showtimes: "17:00, 19:50, 23:05" }
            ].map((movie, index) => (
              <div
                key={index}
                className="group relative w-full max-w-sm mx-auto overflow-hidden bg-white rounded-lg shadow-lg hover:shadow-2xl transition-shadow duration-300 transform hover:scale-105 no-nav" // Added no-nav class
              >
                <img
                  className="object-cover w-full h-80 group-hover:scale-110 transition-transform duration-300"
                  src={movie.image}
                  alt={`Movie ${index + 1}`}
                />
                <div className="p-5 bg-white">
                  <h2 className="text-lg font-bold text-black uppercase group-hover:text-yellow-500 transition-colors duration-300">
                    {movie.title}
                  </h2>
                  <p className="mt-2 text-sm text-gray-600">Showtimes: {movie.showtimes}</p>
                </div>
              </div>
            ))}
          </div>
        </div>

        {/* Conditional Carousel Rendering */}
        <Carousel />

        {/* Additional Section - Cinema Info */}
        <div className="bg-white text-black py-8 mt-4 rounded-lg shadow-lg flex flex-col md:flex-row items-center space-y-6 md:space-y-0 md:space-x-8">
          <div className="md:w-1/2">
            <img
              src={filmimg}
              alt="Cinema Hall"
              className="w-[750px] h-auto rounded-lg shadow-lg transform hover:scale-105 transition-transform duration-300 ml-4"
            />
          </div>
          <div className="md:w-1/2 mt-8 md:mt-0 md:pl-8 text-left">
            <h2 className="text-3xl md:text-4xl font-bold mb-4 leading-tight">
              A Few Words About Aurora Cinema
            </h2>
            <p className="text-lg mb-4 leading-relaxed">
              Aurora Cinema is part of the vibrant Audrey Mall, a premier shopping and entertainment destination located in downtown San Diego's bustling Yerba Buena Neighborhood...
            </p>
            <p className="text-lg leading-relaxed">
              Experience the joy of shopping and entertainment in one place, with exclusive offers and events happening year-round...
            </p>
          </div>
        </div>

        <Slides />
        <Review />
      </div>
    </div>
  );
};

export default Home;
