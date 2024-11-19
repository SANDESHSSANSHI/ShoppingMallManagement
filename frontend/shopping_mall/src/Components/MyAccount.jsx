// src/Components/MyAccount.jsx
import React, { useState } from 'react';
import {
    Dialog,
    DialogActions,
    DialogContent,
    DialogTitle,
    TextField,
    Button,
} from '@mui/material';
import { getuser, setuser } from '../Functions/userdata'; // Adjust path to where userdata.js is located
import { useNavigate } from 'react-router-dom'; // Import useNavigate


const MyAccount = () => {
    const userDetails = getuser(); // Use getuser to retrieve user details
    const { type, id } = getuser(); 

    const navigate = useNavigate(); // Initialize useNavigate hook

    const [open, setOpen] = useState(false);
    const [newName, setNewName] = useState(userDetails.name);
    const [newMobile, setNewMobile] = useState(userDetails.mobileNumber);
    const [newMail, setNewMail] = useState(userDetails.mail);
    const [newPassword, setNewPassword] = useState(''); // Password to be updated

    const handleClickOpen = () => setOpen(true);
    const handleClose = () => setOpen(false);

    const handleUpdateDetails = () => {
        setuser({
            ...userDetails,
            name: newName, // Update name
            mobileNumber: newMobile, // Update mobile number
            mail: newMail, // Update email
            password: newPassword || userDetails.password, // Update password if provided, else keep old password
            // Do not change ID
        });
        handleClose(); // Close dialog after updating
    };

    const handleViewOrders = () => {
        navigate('/my-orders'); 
    };

    return (
        <div className="flex justify-center items-start w-full h-screen bg-gradient-to-br from-blue-50 to-white pt-10">
            <div className="bg-white rounded-lg shadow-lg p-8 w-96 transition-transform transform hover:scale-105">
                <h2 className="text-3xl font-bold mb-6 text-blue-600 text-center">My Account</h2>

                <p className="text-lg text-gray-700">Type: <span className="font-semibold">{userDetails.type}</span></p>
                <p className="text-lg text-gray-700">ID: <span className="font-semibold">{userDetails.id}</span></p>
                <p className="text-lg text-gray-700">Name: <span className="font-semibold">{userDetails.name}</span></p>
                <p className="text-lg text-gray-700">Email: <span className="font-semibold">{userDetails.mail}</span></p>
                <p className="text-lg text-gray-700">Contact: <span className="font-semibold">{userDetails.mobileNumber}</span></p>

                <button
                    onClick={handleClickOpen}
                    className="bg-blue-600 text-white py-2 px-4 rounded mt-4 w-full hover:bg-blue-500 transition duration-300"
                >
                    Update Details
                </button>

                {type === "Customer" && (
                <button
                    onClick={handleViewOrders}
                    className="bg-green-600 text-white py-2 px-4 rounded mt-4 w-full hover:bg-green-500 transition duration-300"
                >
                    View My Orders
                </button>)}

               
            </div>

            <Dialog open={open} onClose={handleClose}>
                <DialogTitle className="text-lg font-semibold text-center text-blue-600">Update User Details</DialogTitle>
                <DialogContent className="bg-gray-100">
                    <TextField
                        autoFocus
                        margin="dense"
                        label="Name"
                        fullWidth
                        variant="outlined"
                        value={newName}
                        onChange={(e) => setNewName(e.target.value)}
                        className="mb-4"
                    />
                    <TextField
                        margin="dense"
                        label="Email"
                        fullWidth
                        variant="outlined"
                        value={newMail}
                        onChange={(e) => setNewMail(e.target.value)}
                        className="mb-4"
                    />
                    <TextField
                        margin="dense"
                        label="Mobile Number"
                        fullWidth
                        variant="outlined"
                        value={newMobile}
                        onChange={(e) => setNewMobile(e.target.value)}
                        className="mb-4"
                    />
                    <TextField
                        margin="dense"
                        label="Password"
                        type="password"
                        fullWidth
                        variant="outlined"
                        value={newPassword}
                        onChange={(e) => setNewPassword(e.target.value)}
                        className="mb-4"
                    />
                </DialogContent>
                <DialogActions className="flex justify-between">
                    <button
                        onClick={handleClose}
                        className="text-blue-600 hover:text-blue-800 transition"
                    >
                        Cancel
                    </button>
                    <button
                        onClick={handleUpdateDetails}
                        className="bg-blue-600 text-white py-2 px-4 rounded hover:bg-blue-500 transition"
                    >
                        Confirm
                    </button>
                </DialogActions>
            </Dialog>
        </div>
    );
};

export default MyAccount;
