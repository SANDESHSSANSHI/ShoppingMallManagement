// AddToCart.jsx
import React, { useState } from 'react';
import axios from 'axios';
import { Button, Snackbar, Alert } from '@mui/material';

const AddToCart = ({ shopId, customerId, itemId }) => {
  const [cartItem, setCartItem] = useState({
    cartid: 808, // Replace with dynamic value as necessary
    customerId: customerId,
    shopId: shopId,
    itemIds: [], // To hold the selected item IDs
  });
  console.log("shopID in add to cart :",shopId)
  console.log("customerId in add to cart:",customerId)
  console.log("itemID in add to cart:",itemId)

  const [successMessage, setSuccessMessage] = useState('');
  const [errorMessage, setErrorMessage] = useState('');

  const handleAddToCart = async () => {
    // Update cartItem's itemIds with the selected itemId
    setCartItem((prevCart) => ({
      ...prevCart,
      itemIds: [...prevCart.itemIds, itemId], // Add item ID to the cart
    }));

    // Post cartItem to the server
    try {
      await axios.post(`/carts`, cartItem); // Adjust the endpoint as necessary
      setSuccessMessage('Item added to cart successfully!');
    } catch (err) {
      setErrorMessage(err.response?.data?.message || err.message);
    }
  };

  const handleCloseSnackbar = () => {
    setSuccessMessage('');
    setErrorMessage('');
  };

  return (
    <>
      <Button
        variant="contained"
        color="primary"
        onClick={handleAddToCart}
        style={{ marginTop: '10px' }}
      >
        Add to Cart
      </Button>

      {successMessage && (
        <Snackbar open={Boolean(successMessage)} autoHideDuration={6000} onClose={handleCloseSnackbar}>
          <Alert onClose={handleCloseSnackbar} severity="success" sx={{ width: '100%' }}>
            {successMessage}
          </Alert>
        </Snackbar>
      )}

      {errorMessage && (
        <Snackbar open={Boolean(errorMessage)} autoHideDuration={6000} onClose={handleCloseSnackbar}>
          <Alert onClose={handleCloseSnackbar} severity="error" sx={{ width: '100%' }}>
            Error: {errorMessage}
          </Alert>
        </Snackbar>
      )}
    </>
  );
};



const BuyNow = () => {
  return (
    <>
      <Button
        variant="contained"
        color="primary"
        style={{ marginTop: '10px' }}
      >
        Buy Now
      </Button>

 
    </>
  );
};
export {AddToCart,BuyNow} ;
