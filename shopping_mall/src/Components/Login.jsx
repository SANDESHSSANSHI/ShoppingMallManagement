import React, { useState, useEffect, useContext } from 'react';
import { useNavigate } from 'react-router-dom';
import checkcredentials from '../Functions/checkcredentials';
import 'bootstrap/dist/css/bootstrap.min.css';
import { motion } from 'framer-motion';
import { AuthContext } from './ContextAPI/AuthContext'; // Import AuthContext
import axios from 'axios'; // Import axios for API requests

const Login = () => {
  const navigate = useNavigate();
  const { login, register } = useContext(AuthContext); // Destructure login and register from context
  const [userType, setUserType] = useState('');
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const [success, setSuccess] = useState('');
  const [registerData, setRegisterData] = useState({
    name: '',
    type: '',
    password: '',
    mobileNumber: '',
    mail: ''
  });
  const [isRegisterMode, setIsRegisterMode] = useState(false);
  const [showText, setShowText] = useState(true);
  const text = "Shoppers'STOP";

  const handleUserTypeChange = (event) => {
    setUserType(event.target.value);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (username && password && userType) {
      console.log("login input  details:"+userType,username,password);

      const result = await checkcredentials(username, password, userType);

      console.log("result:"+result);
      
      
      if (result) {
        const [id, type] = result;
        login(type); // Call login function from context

        // Navigate based on user type
        if (type === 'Customer') navigate('/Mall/Customer');
        if (type === 'MallAdmin') navigate('/Mall/MallAdmin');
        if (type === 'ShopOwner') navigate('/Mall/ShopOwner');
      } else {
        setError('Invalid credentials. Please try again.');
      }
    } else {
      setError('Please fill in all fields.');
    }
  };

  // Handle Register Form Input
  const handleRegisterInputChange = (e) => {
    setRegisterData({
      ...registerData,
      [e.target.name]: e.target.value
    });
  };

  // Handle Register Submit
  const handleRegisterSubmit = async (e) => {
    e.preventDefault();
    console.log("reg:"+registerData);
    
    try {
      const response = await axios.post('/users', registerData); // POST request to register user

      if (response.status === 201) {
        setError('');
        alert('Registration successful!');
        setRegisterData({
          name: '',
          type: '',
          password: '',
          mobileNumber: '',
          mail: ''
        });
      } else {
        setError('Registration failed. Please try again.');
      }
    } catch (error) {
      setError('Error occurred during registration!');
    }
  };

  const handleToggle = () => {
    setIsRegisterMode(!isRegisterMode);
  };

  useEffect(() => {
    const interval = setInterval(() => {
      setShowText(false);
      setTimeout(() => {
        setShowText(true);
      }, 100);
    }, 15000);

    return () => clearInterval(interval);
  }, []);

  return (
    <div className="container-fluid d-flex mt-10 mb-20 justify-content-center align-items-center vh-100 bg-white text-light">
      <div className="row w-100 ">
        {/* Left Column */}
        <div className="col-md-6 d-flex flex-column justify-content-center">
          <h1 className="display-4 font-weight-bold mb-4 text-black" style={{ fontFamily: 'Playfair Display, serif' }}>
            {text.split("").map((letter, index) => (
              <motion.span
                key={index}
                initial={{ y: 50, opacity: 0 }}  // Start position
                animate={showText ? { y: 0, opacity: 1 } : { y: 50, opacity: 0 }} // Bounce effect
                transition={{ duration: 0.5, type: 'spring', stiffness: 500, damping: 20, delay: index * 0.05 }}
              >
                {letter}
              </motion.span>
            ))}
          </h1>
          <p className="lead text-black">
            Elevate your shopping experience with unmatched convenience, curated services, and a commitment to your satisfaction.
          </p>
        </div>

        {/* Right Column */}
        <div className="col-md-6 d-flex justify-content-center align-items-center">
          <div className="card p-4 bg-secondary bg-opacity-75 text-white" style={{ width: '400px', borderRadius: '10px' }}>
            <h2 className="text-center mb-4">{isRegisterMode ? 'REGISTER' : 'LOGIN'}</h2>

            {error && (
              <div className="alert alert-danger" role="alert">
                {error}
              </div>
            )}
            {success && (
              <div className="alert alert-success" role="alert">
                {success}
              </div>
            )}

            {/* Toggle Switch */}
            <div className="d-flex justify-content-center mb-3">
              <label className="switch">
                <input
                  type="checkbox"
                  checked={isRegisterMode}
                  onChange={handleToggle}
                />
                <span className="slider"></span>
              </label>
            </div>

            <form onSubmit={isRegisterMode ? handleRegisterSubmit : handleSubmit}>
              {isRegisterMode ? (
                <>
                  {/* Registration Form Fields */}
                  <div className="mb-3">
                    <label htmlFor="name" className="form-label">Name</label>
                    <input
                      id="name"
                      name="name"
                      type="text"
                      className="form-control"
                      value={registerData.name}
                      onChange={handleRegisterInputChange}
                      required
                      style={{ padding: '10px', borderRadius: '5px' }}
                    />
                  </div>
                  <div className="mb-3">
                    <label htmlFor="mobileNumber" className="form-label">Mobile Number</label>
                    <input
                      id="mobileNumber"
                      name="mobileNumber"
                      type="text"
                      className="form-control"
                      value={registerData.mobileNumber}
                      onChange={handleRegisterInputChange}
                      required
                      style={{ padding: '10px', borderRadius: '5px' }}
                    />
                  </div>
                  <div className="mb-3">
                    <label htmlFor="mail" className="form-label">Email</label>
                    <input
                      id="mail"
                      name="mail"
                      type="email"
                      className="form-control"
                      value={registerData.mail}
                      onChange={handleRegisterInputChange}
                      required
                      style={{ padding: '10px', borderRadius: '5px' }}
                    />
                  </div>
                  <div className="mb-3">
                    <label htmlFor="password" className="form-label">Password</label>
                    <input
                      id="password"
                      name="password"
                      type="password"
                      className="form-control"
                      value={registerData.password}
                      onChange={handleRegisterInputChange}
                      required
                      style={{ padding: '10px', borderRadius: '5px' }}
                    />
                  </div>
                  <div className="mb-3">
                    <label htmlFor="type" className="form-label">User Type</label>
                    <select
                      id="type"
                      name="type"
                      className="form-select"
                      value={registerData.type}
                      onChange={handleRegisterInputChange}
                      required
                      style={{ padding: '10px', borderRadius: '5px' }}
                    >
                      <option value="">Select User Type</option>
                      <option value="ShopOwner">ShopOwner</option>
                      <option value="MallAdmin">MallAdmin</option>
                      <option value="Customer">Customer</option>
                    </select>
                  </div>
                  <button type="submit" className="btn btn-primary w-100" style={{ padding: '10px', borderRadius: '5px' }}>
                    Register
                  </button>
                </>
              ) : (
                <>
                  {/* Login Form Fields */}
                  <div className="mb-3">
                    <label htmlFor="username" className="form-label">Username</label>
                    <input
                      id="username"
                      type="text"
                      className="form-control"
                      value={username}
                      onChange={(e) => setUsername(e.target.value)}
                      required
                      style={{ padding: '10px', borderRadius: '5px' }}
                    />
                  </div>
                  <div className="mb-3">
                    <label htmlFor="password" className="form-label">Password</label>
                    <input
                      id="password"
                      type="password"
                      className="form-control"
                      value={password}
                      onChange={(e) => setPassword(e.target.value)}
                      required
                      style={{ padding: '10px', borderRadius: '5px' }}
                    />
                  </div>
                  <div className="mb-3">
                    <label htmlFor="userType" className="form-label">User Type</label>
                    <select
                      id="userType"
                      value={userType}
                      onChange={handleUserTypeChange}
                      className="form-select"
                      required
                      style={{ padding: '10px', borderRadius: '5px' }}
                    >
                      <option value="">Select User Type</option>
                      <option value="Customer">Customer</option>
                      <option value="MallAdmin">MallAdmin</option>
                      <option value="ShopOwner">ShopOwner</option>
                    </select>
                  </div>
                  <button type="submit" className="btn btn-primary w-100" style={{ padding: '10px', borderRadius: '5px' }}>
                    Login
                  </button>
                </>
              )}
            </form>
          </div>
        </div>
      </div>
      {/* CSS for the Toggle Switch */}
      <style>
        {`
          .switch {
            position: relative;
            display: inline-block;
            width: 60px;
            height: 34px;
          }

          .switch input {
            opacity: 0;
            width: 0;
            height: 0;
          }

          .slider {
            position: absolute;
            cursor: pointer;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
            background-color: #ccc;
            transition: .4s;
            border-radius: 34px;
          }

          .slider:before {
            position: absolute;
            content: "";
            height: 26px;
            width: 26px;
            left: 4px;
            bottom: 4px;
            background-color: white;
            transition: .4s;
            border-radius: 50%;
          }

          input:checked + .slider {
            background-color: #2196F3;
          }

          input:checked + .slider:before {
            transform: translateX(26px);
          }
        `}
      </style>
    </div>
  );
};

export default Login;
