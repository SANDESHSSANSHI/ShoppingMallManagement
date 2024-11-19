import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { getuser } from '../Functions/userdata';
import { useParams, useNavigate, useLocation } from 'react-router-dom';
import {
  Typography,
  CircularProgress,
  Snackbar,
  Alert,
  Grid,
  Paper,
  Button
} from '@mui/material';
import { AddToCart, BuyNow } from './AddToCart';
import { deleteItem } from './Delete/delete';
import AddItemDialog from './AddItemDialog'; // Import AddItemDialog for adding and updating items

const Items = () => {
  const { shopId } = useParams(); // Get shopId from URL
  
  const location = useLocation();
  const queryParams = new URLSearchParams(location.search);
  const type = queryParams.get('type'); // Get user type from URL
  const customerId = queryParams.get("id"); // Assuming the customerId is in the URL
  const shopOwnerId = queryParams.get("shopOwnerId");
  const shopName = queryParams.get("shopName");

  const navigate = useNavigate();

  const [items, setItems] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [successMessage, setSuccessMessage] = useState('');
  const [openDialog, setOpenDialog] = useState(false); // To control dialog visibility
  const [selectedItem, setSelectedItem] = useState(null); // To store the item being updated

  useEffect(() => {
    const fetchItems = async () => {
      try {
        const response = await axios.get(`/shops/${shopId}`);
        if (Array.isArray(response.data.items)) {
          setItems(response.data.items);
        } else {
          setError('Invalid response format');
        }
      } catch (err) {
        setError(err.response?.data?.message || err.message);
      } finally {
        setLoading(false);
      }
    };
    fetchItems();
  }, [shopId]);

  const handleDelete = async (itemId) => {
    try {
      await deleteItem(itemId); // Call deleteItem function
      setItems((prevItems) => prevItems.filter(item => item.itemId !== itemId)); // Update the state
      setSuccessMessage('Item deleted successfully!');
    } catch (err) {
      setError(err.message);
    }
  };

  const handleAddOrUpdateClick = (item = null) => {
    setSelectedItem(item); // If item is null, it means we're adding a new item
    setOpenDialog(true);
  };

  const handleCloseDialog = () => {
    setOpenDialog(false);
    setSelectedItem(null); // Reset the selected item after closing
  };

  const handleDialogSuccess = (newItem) => {
    if (selectedItem) {
      // If we're updating an item, replace the old item with the new one
      setItems((prevItems) => 
        prevItems.map(item => item.itemId === newItem.itemId ? newItem : item)
      );
      setSuccessMessage('Item updated successfully!');
    } else {
      // If we're adding a new item, simply append it
      setItems((prevItems) => [...prevItems, newItem]);
      setSuccessMessage('Item added successfully!');
    }
    handleCloseDialog(); // Close the dialog
  };

  const generateUniqueId = (length) => {
    const min = Math.pow(10, length - 1);
    const max = Math.pow(10, length) - 1;
    return Math.floor(Math.random() * (max - min + 1)) + min;
  };

  let newcart = {};
  const addtocartclick = async (customerId, shopId, itemId, e) => {
    e.preventDefault();

    if (newcart.customerId === undefined) {
      newcart = {
        cartid: generateUniqueId(8),
        customerId: customerId,
        shopId: shopId,
        itemIds: [itemId],
      };
    } else {
      newcart.itemIds.push(itemId);
    }
  };

  const pushcart = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post(`/carts`, newcart);
      alert("Successfully added to cart!");

      if (response.status === 201) {
        // Navigate to the Order component with the cartId only
        navigate(`/order/${newcart.cartid}`);

        // Reset the newcart object after success
        newcart = {};
      }
    } catch (err) {
      setError(err.response?.data?.message || err.message + " Please add the item again..!");
    }
  };

  if (loading) return <div className="flex justify-center items-center h-screen"><CircularProgress /></div>;

  return (
    <div className="w-11/12 mx-auto"> {/* Set a max width and center the container */}
      <Typography
        variant="h4"
        gutterBottom
        className="text-2xl bg-blue-200 p-4 rounded shadow-md hover:bg-blue-300 transition duration-300 ease-in-out"
      >
        Shop: {shopName}
      </Typography>


      {shopOwnerId === customerId && (
        <Button
          variant="contained"
          color="primary"
          className="bg-green-500 hover:bg-green-600 shadow-lg transition duration-300 ease-in-out"
          onClick={() => handleAddOrUpdateClick()}
        >
          Add New Item
        </Button>
      )}
      {error && (
        <Snackbar open={Boolean(error)} autoHideDuration={6000} onClose={() => setError(null)}>
          <Alert onClose={() => setError(null)} severity="error" sx={{ width: '100%' }}>
            Error: {error}
          </Alert>
        </Snackbar>
      )}

      {successMessage && (
        <Snackbar open={Boolean(successMessage)} autoHideDuration={6000} onClose={() => setSuccessMessage('')}>
          <Alert onClose={() => setSuccessMessage('')} severity="success" sx={{ width: '100%' }}>
            {successMessage}
          </Alert>
        </Snackbar>
      )}

<div className="grid gap-3 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-3"> {/* Use Tailwind CSS grid for layout */}
  {items.length > 0 ? (
    items.map((item) => (
      <div key={item.itemId} className="bg-white p-3 shadow-lg rounded-lg flex flex-col items-center space-y-4">
        {/* <Typography variant="body1">Item ID: {item.itemId}</Typography> */}
        <Typography variant="h5" className="text-lg font-semibold">
           {item.itemName}
        </Typography>
        <Typography
          variant="body1"
          className="text-lg text-gray-700 font-medium hover:text-gray-900 transition duration-300 ease-in-out"
        >
          Price: ₹{item.itemPrice}/-
        </Typography>

        <img
          src={`data:image/jpeg;base64,${item.image}`}
          alt={item.itemName}
          className="w-24 h-24 object-cover mx-auto my-4 hover:shadow-lg hover:scale-105 transition-transform duration-300 ease-in-out"
        />

        {type === 'Customer' && (
          <span className="addtocart" onClick={(e) => addtocartclick(customerId, shopId, item.itemId, e)}>
            <AddToCart
              shopId={shopId}
              customerId={customerId}
              itemId={item.itemId}
              className="hover:text-green-600"
            />
          </span>
        )}

        {shopOwnerId === customerId && (
          <>
            <Button
              variant="contained"
              color="secondary"
              className="bg-red-500 hover:bg-red-600 shadow-lg transition duration-300 ease-in-out mt-2 w-full"
              onClick={() => handleDelete(item.itemId)}
            >
              Delete Item
            </Button>

            <Button
              variant="contained"
              color="primary"
              className="bg-blue-500 hover:bg-blue-600 shadow-lg transition duration-300 ease-in-out mt-2 w-full"
              onClick={() => handleAddOrUpdateClick(item)}
            >
              Update Item
            </Button>
          </>
        )}
      </div>
    ))
  ) : (
    <Typography variant="h6" className="col-span-full text-center">No items found for this shop.</Typography>
  )}
</div>


      {type === 'Customer' && (
        <div className="flex justify-center my-4"> {/* Center the button horizontally */}
          <span 
            className="buynow inline-block px-6 py-3 my-2 text-center text-white rounded hover:transition-all duration-300 cursor-pointer"
            onClick={(e) => pushcart(e)}
          >
            <BuyNow />
          </span>
        </div>
      )}

      {/* Add/Update Dialog */}
      <AddItemDialog
        open={openDialog}
        onClose={handleCloseDialog}
        onSuccess={handleDialogSuccess} // Pass the success handler
        shopId={shopId}
        item={selectedItem} // Pass the selected item if updating, or null if adding a new item
      />
    </div>
  );
};

export default Items;
