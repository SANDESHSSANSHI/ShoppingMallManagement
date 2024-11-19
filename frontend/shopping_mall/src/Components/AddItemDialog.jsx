import React, { useEffect, useState } from 'react';
import {
  Dialog,
  DialogTitle,
  DialogContent,
  DialogActions,
  Button,
  TextField,
  Snackbar,
  Alert
} from '@mui/material';
import axios from 'axios';

const AddItemDialog = ({ open, onClose, onSuccess, shopId, item }) => {
  const [itemId, setItemId] = useState('');  // Add itemId state
  const [itemName, setItemName] = useState('');
  const [itemManufacture, setItemManufacture] = useState('');
  const [itemExpiryDate, setItemExpiryDate] = useState('');
  const [itemPrice, setItemPrice] = useState('');
  const [itemCategory, setItemCategory] = useState(''); 
  const [itemImage, setItemImage] = useState(null);
  const [error, setError] = useState(null);
  const [successMessage, setSuccessMessage] = useState('');

  useEffect(() => {
    if (item) {
      // Populate fields for updating an item
      setItemId(item.itemId); // Set existing itemId
      setItemName(item.itemName);
      setItemManufacture(item.itemManufacture);
      setItemExpiryDate(item.itemExpiryDate);
      setItemPrice(item.itemPrice);
      setItemCategory(item.itemCategory);
      setItemImage(item.image);
    } else {
      // Reset fields for adding a new item
      setItemId(generateUniqueId(5));  // Generate a new itemId
      setItemName('');
      setItemManufacture('');
      setItemExpiryDate('');
      setItemPrice('');
      setItemCategory('');
      setItemImage(null);
    }
  }, [item, open]);

  // Function to generate a unique itemId
  const generateUniqueId = (length) => {
    const min = Math.pow(10, length - 1);
    const max = Math.pow(10, length) - 1;
    return Math.floor(Math.random() * (max - min + 1)) + min;
  };

  const handleAddItem = async () => {
    const formData = new FormData();
    formData.append('itemId', itemId);  // Add itemId to formData
    formData.append('itemName', itemName);
    formData.append('itemManufacture', itemManufacture);
    formData.append('itemExpiryDate', itemExpiryDate);
    formData.append('itemPrice', itemPrice);
    formData.append('itemCategory', itemCategory);
    formData.append('shopId', shopId);
  
    if (itemImage) {
      formData.append('image', itemImage); 
    }
  
    try {
      const response = item
        ? await axios.put(`/items/${itemId}`, formData)  // Update existing item
        : await axios.post(`/items`, formData); // Add new item using POST to /items
  
      if (response.status === 201 || response.status === 200) {
        setSuccessMessage(item ? 'Item updated successfully!' : 'Item added successfully!');
        onSuccess(response.data); // Pass new or updated item data back
      }
    } catch (err) {
      setError(err.response?.data?.message || err.message);
    }
  };
  

  return (
    <Dialog open={open} onClose={onClose}>
      <DialogTitle>{item ? 'Update Item' : 'Add New Item'}</DialogTitle>
      <DialogContent>
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

        {/* Item ID (display but not editable for updates) */}
        <TextField
          margin="dense"
          label="Item ID"
          fullWidth
          variant="outlined"
          value={itemId}
          disabled={!!item} // Disable input if updating
        />

        <TextField
          margin="dense"
          label="Item Name"
          fullWidth
          variant="outlined"
          value={itemName}
          onChange={(e) => setItemName(e.target.value)}
        />
        <TextField
          margin="dense"
          label="Manufacture"
          fullWidth
          variant="outlined"
          value={itemManufacture}
          onChange={(e) => setItemManufacture(e.target.value)}
        />
        <TextField
          margin="dense"
          label="Expiry Date"
          fullWidth
          variant="outlined"
          type='date'
          value={itemExpiryDate}
          onChange={(e) => setItemExpiryDate(e.target.value)}
        />
        <TextField
          margin="dense"
          label="Price"
          type="number"
          fullWidth
          variant="outlined"
          value={itemPrice}
          onChange={(e) => setItemPrice(e.target.value)}
        />
        <TextField
          margin="dense"
          label="Category"
          fullWidth
          variant="outlined"
          value={itemCategory}
          onChange={(e) => setItemCategory(e.target.value)}
        />
        <input
          type="file"
          accept="image/*"
          onChange={(e) => setItemImage(e.target.files[0])} 
        />
      </DialogContent>
      <DialogActions>
        <Button onClick={onClose} color="primary">
          Cancel
        </Button>
        <Button onClick={handleAddItem} color="primary">
          {item ? 'Update' : 'Add'} Item
        </Button>
      </DialogActions>
    </Dialog>
  );
};

export default AddItemDialog;
