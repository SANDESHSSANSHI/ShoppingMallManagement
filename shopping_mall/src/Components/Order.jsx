import React, { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import axios from 'axios';
import {
  Button,
  Dialog,
  DialogActions,
  DialogContent,
  DialogContentText,
  DialogTitle,
  TextField,
  MenuItem,
  Typography,
} from '@mui/material';
import { getuser } from '../Functions/userdata';

const Order = () => {
  const { cartId } = useParams();
  const navigate = useNavigate();
  const [cartDetails, setCartDetails] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [open, setOpen] = useState(false);
  const [thankYouOpen, setThankYouOpen] = useState(false);
  const [dateOfPurchase, setDateOfPurchase] = useState('');
  const [paymentMode, setPaymentMode] = useState('Cash on Delivery');
  const [orderData, setOrderData] = useState({});
  const { type } = getuser();

  useEffect(() => {
    const fetchCartDetails = async () => {
      try {
        const response = await axios.get(`/carts/${cartId}`);
        setCartDetails(response.data);
      } catch (err) {
        setError('Failed to fetch cart details');
      } finally {
        setLoading(false);
      }
    };

    fetchCartDetails();
  }, [cartId]);

  const handleOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };

  const generateUniqueOrderId = (length) => {
    const min = Math.pow(10, length - 1);
    const max = Math.pow(10, length) - 1;
    return Math.floor(Math.random() * (max - min + 1)) + min;
  };

  const handleConfirm = async () => {
    try {
      // Generate a unique order ID
      const id = generateUniqueOrderId(8);
      
      // Set the order data
      const newOrderData = {
        orderId: id,
        cartId: cartId,
        dateOfPurchase,
        paymentMode,
      };
      setOrderData(newOrderData); // Update the order data state

      // Send the order data to the backend
      await axios.post('/orderdetails', newOrderData); // Use the new order data here
      setThankYouOpen(true);
    } catch (error) {
      console.error('Error confirming order:', error);
      alert('Failed to confirm order.');
    } finally {
      handleClose();
    }
  };

  const handleThankYouClose = () => {
    navigate(`/Mall/${type}`);
  };

  if (loading) {
    return <p>Loading cart details...</p>;
  }

  if (error) {
    return <p>{error}</p>;
  }

  return (
    <div className="flex flex-col items-center justify-center min-h-screen bg-gray-50 p-4 pt-4">
      {cartDetails ? (
        <div className="flex items-center w-full justify-center">
          <div className="bg-white p-6 rounded-lg shadow-lg w-full max-w-2xl text-center">
            <h1 className="text-3xl font-bold mb-6">Order Details</h1>
            <h2 className="text-xl font-semibold">Cart ID: {cartDetails.cartid}</h2>
            <p><strong>Customer ID:</strong> {cartDetails.customerId}</p>
            <p><strong>Name:</strong> {cartDetails.name}</p>
            <p><strong>Shop ID:</strong> {cartDetails.shopId}</p>
            <p><strong>Shop Name:</strong> {cartDetails.shopName}</p>
            <p><strong>Total Price:</strong> ₹{cartDetails.totalprice}</p>
            <p><strong>Number of Items:</strong> {cartDetails.no_ofitems}</p>
            <Button onClick={handleOpen}>
              Confirm Order
            </Button>
          </div>
        </div>
      ) : (
        <p>No cart details available.</p>
      )}

      <Dialog open={open} onClose={handleClose}>
        <DialogTitle className="text-lg font-bold">Confirm Order</DialogTitle>
        <DialogContent>
          <DialogContentText>
            Please enter the date of purchase and select the payment mode.
          </DialogContentText>
          <TextField
            autoFocus
            margin="dense"
            label="Date of Purchase"
            type="date"
            fullWidth
            value={dateOfPurchase}
            onChange={(e) => setDateOfPurchase(e.target.value)}
            InputLabelProps={{
              shrink: true,
            }}
            className="mt-2"
          />
          <TextField
            select
            margin="dense"
            label="Payment Mode"
            fullWidth
            value={paymentMode}
            onChange={(e) => setPaymentMode(e.target.value)}
            className="mt-4"
          >
            <MenuItem value="Cash on Delivery">Cash on Delivery</MenuItem>
            <MenuItem value="Online">Online</MenuItem>
          </TextField>
        </DialogContent>
        <DialogActions>
          <Button onClick={handleClose} >
            Cancel
          </Button>
          <button
  onClick={handleConfirm}
  className="bg-green-600 text-white hover:bg-green-700 transition duration-300 ease-in-out font-semibold rounded-md shadow-sm px-2.5 py-1.5 text-sm transform hover:scale-105"
>
  CONFIRM
</button>


        </DialogActions>
      </Dialog>

      <Dialog open={thankYouOpen} onClose={handleThankYouClose}>
  <DialogTitle className="text-2xl font-bold text-center text-blue-600 border-b-2 border-blue-300 pb-4">
    Thank You!
  </DialogTitle>
  <DialogContent className="bg-gray-100 p-6 rounded-lg shadow-lg">
    <DialogContentText className="text-gray-700 text-lg text-center">
      Your order has been confirmed successfully. Thank you for shopping with us!
    </DialogContentText>
    <Typography variant="h6" align="center" className="mt-4 font-semibold text-blue-500">
      Your Order ID: <span className="text-blue-600 font-bold">{orderData.orderId}</span>
    </Typography>
  </DialogContent>
  <DialogActions className="justify-center mb-4">
    <button
      onClick={handleThankYouClose}
      className="bg-blue-600 text-white hover:bg-blue-700 transition duration-300 ease-in-out rounded-lg shadow-md px-4 py-2 transform hover:scale-105"
    >
      Close
    </button>
  </DialogActions>
</Dialog>

    </div>
  );
};

export default Order;
