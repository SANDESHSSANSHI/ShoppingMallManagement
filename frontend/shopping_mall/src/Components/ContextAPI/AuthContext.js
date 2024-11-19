import React, { createContext, useState, useEffect } from 'react';

// Create the AuthContext
const AuthContext = createContext();

// Create a provider component
const AuthProvider = ({ children }) => {
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  // Check the login status from localStorage when the provider mounts
  useEffect(() => {
    const loggedInStatus = localStorage.getItem('isLoggedIn') === 'true';
    setIsLoggedIn(loggedInStatus);
  }, []);

  // Function to log in
  const login = (userType) => {
    localStorage.setItem('isLoggedIn', 'true');
    localStorage.setItem('userType', userType);
    setIsLoggedIn(true);
  };

  // Function to log out
  const logout = () => {
    localStorage.removeItem('isLoggedIn');
    localStorage.removeItem('userType');
    setIsLoggedIn(false);
  };

  return (
    <AuthContext.Provider value={{ isLoggedIn, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
};

export { AuthContext, AuthProvider };
