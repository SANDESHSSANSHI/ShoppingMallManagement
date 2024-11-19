import React from 'react';
import logo from '../pics/logoonlyss.png'; // Assuming this is the same logo as in the Header

const Footer = () => {
  return (
    <div className="bg-gray-900 text-white py-4 px-4"> {/* Reduced py-6 to py-4 */}
      <div className="max-w-7xl mx-auto grid grid-cols-1 md:grid-cols-4 gap-4"> {/* Reduced gap-6 to gap-4 */}
        {/* Logo and Description */}
        <div>
          <div className="flex items-center mb-1"> {/* Reduced mb-2 to mb-1 */}
            <img src={logo} alt="Shoppers'STOP Logo" className="h-8 w-8" /> {/* Reduced h-10 to h-8 */}
            <h1 className="text-lg font-bold ml-1"> {/* Reduced text-xl to text-lg */}
              <span className="text-purple-500">Shoppers'</span>STOP
            </h1>
          </div>
          <p className="text-gray-400 mb-1 text-sm"> {/* Reduced mb-2 to mb-1 and added text-sm */}
            Shoppers'STOP is the most exclusive shopping mall and entertainment center.
          </p>
          <div className="flex space-x-2 mt-1"> {/* Reduced mt-2 to mt-1 and space-x-3 to space-x-2 */}
            <a href="#" className="text-gray-400 hover:text-white text-sm">
              <i className="fab fa-facebook-f"></i>
            </a>
            <a href="#" className="text-gray-400 hover:text-white text-sm">
              <i className="fab fa-twitter"></i>
            </a>
            <a href="#" className="text-gray-400 hover:text-white text-sm">
              <i className="fab fa-youtube"></i>
            </a>
            <a href="#" className="text-gray-400 hover:text-white text-sm">
              <i className="fab fa-linkedin-in"></i>
            </a>
            <a href="#" className="text-gray-400 hover:text-white text-sm">
              <i className="fab fa-google-plus-g"></i>
            </a>
          </div>
        </div>

        {/* Navigation Links */}
        <div>
          <h3 className="text-gray-400 uppercase mb-1 text-sm">Navigation</h3> {/* Reduced mb-2 to mb-1 and added text-sm */}
          <ul className="space-y-0.5"> {/* Changed to space-y-0.5 for tighter spacing */}
            <li><a href="/" className="hover:text-gray-300 text-sm">Home</a></li>
            <li><a href="/faqs" className="hover:text-gray-300 text-sm">FAQs</a></li>
            <li><a href="/about" className="hover:text-gray-300 text-sm">About</a></li>
            <li><a href="/contact" className="hover:text-gray-300 text-sm">Contact Us</a></li>
          </ul>
        </div>

        {/* Customer Service */}
        <div>
          <h3 className="text-gray-400 uppercase mb-1 text-sm">Customer Service</h3> {/* Reduced mb-2 to mb-1 and added text-sm */}
          <ul className="space-y-0.5"> {/* Changed to space-y-0.5 for tighter spacing */}
            <li><a href="/about" className="hover:text-gray-300 text-sm">About us</a></li>
            <li><a href="/faqs" className="hover:text-gray-300 text-sm">FAQ</a></li>
            <li><a href="/contact" className="hover:text-gray-300 text-sm">Contacts</a></li>
          </ul>
        </div>

        {/* Working Hours */}
        <div>
          <h3 className="text-gray-400 uppercase mb-1 text-sm">Working Hours</h3> {/* Reduced mb-2 to mb-1 and added text-sm */}
          <ul className="space-y-0.5"> {/* Changed to space-y-0.5 for tighter spacing */}
            <li>
              <span className="text-gray-400">Shopping</span><br />
              <span>10:00 am - 10:00 pm</span>
            </li>
            <li>
              <span className="text-gray-400">Entertainment Zone</span><br />
              <span>10:00 am - 10:00 pm</span>
            </li>
            <li>
              <span className="text-gray-400">Cinema</span><br />
              <span>10:00 am - 10:00 pm</span>
            </li>
            <li>
              <span className="text-gray-400">Parking</span><br />
              <span>10:00 am - 10:00 pm</span>
            </li>
          </ul>
        </div>
      </div>

      <div className="border-t border-gray-800 mt-2 pt-2 text-center"> 
        <p className="text-gray-500 text-xs">© 2024 Shoppers'STOP - All Rights Reserved</p> 
      </div>
    </div>
  );
};

export default Footer;
