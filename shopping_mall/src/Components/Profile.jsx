// src/Components/Profile.jsx
import React from 'react';
import { Box, Typography, Paper } from '@mui/material';
import { getuser } from '../Functions/userdata'; // Adjust path to where userdata.js is located

const Profile = () => {
    const userDetails = getuser(); // Use getuser to retrieve user details

    return (
        <Box
            className="flex justify-center items-start min-h-screen p-8 bg-gradient-to-r from-gray-100 to-white"
        >
            <Paper
                elevation={6}
                className="p-6 w-full max-w-md text-center rounded-lg shadow-lg transition-transform transform hover:scale-105 hover:shadow-2xl"
            >
                <Typography variant="h4" className="font-bold text-blue-600 mb-4">
                    Profile Details
                </Typography>
                <Typography variant="h6" className="font-medium text-gray-800 mb-2">
                    Type: <span className="font-bold text-gray-900">{userDetails.type}</span> 
                </Typography>
                <Typography variant="h6" className="font-medium text-gray-800 mb-2">
                ID: <span className="font-bold text-gray-900">{userDetails.id}</span>                </Typography>
                <Typography variant="h6" className="font-medium text-gray-800 mb-2">
                    Name: <span className="font-bold text-gray-900">{userDetails.name}</span>
                </Typography>
                <Typography variant="h6" className="font-medium text-gray-800 mb-2">
                    Email: <span className="font-bold text-gray-900">{userDetails.mail}</span>
                </Typography>
                <Typography variant="h6" className="font-medium text-gray-800 mb-2">
                    Contact: <span className="font-bold text-gray-900">{userDetails.mobileNumber}</span>
                </Typography>
            </Paper>
        </Box>
    );
};

export default Profile;
