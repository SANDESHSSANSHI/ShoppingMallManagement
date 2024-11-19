// src/ContextAPI/UserContext.jsx
import React, { createContext, useContext, useState } from 'react';

// Create the User Context
const UserContext = createContext();

// Create a provider component to wrap the app
export const UserProvider = ({ children }) => {
    const [userDetails, setUserDetails] = useState({
        id: '',
        name: '',
        mail: '',
        mobileNumber: '',
        type: '',
    });

    return (
        <UserContext.Provider value={{ userDetails, setUserDetails }}>
            {children}
        </UserContext.Provider>
    );
};

// Custom hook to use the UserContext
export const useUser = () => useContext(UserContext);
