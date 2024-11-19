import React, { useState, useEffect } from 'react';
import { Dialog, DialogTitle, DialogContent, DialogActions, Button, TextField, Snackbar, Alert } from '@mui/material';

const ShopImageDialog = ({ open, onClose, onSubmit, shopData }) => {
  const [shopName, setShopName] = useState(shopData?.shopName || '');
  const [shopStatus, setShopStatus] = useState(shopData?.shopStatus || '');
  const [shopOwnerId, setShopOwnerId] = useState(shopData?.shopOwnerId || '');
  const [mallId, setMallId] = useState(shopData?.mallId || '');
  const [selectedImage, setSelectedImage] = useState(null);
  const [error, setError] = useState(null);

  useEffect(() => {
    if (shopData) {
      setShopName(shopData.shopName);
      setShopStatus(shopData.shopStatus);
      setShopOwnerId(shopData.shopOwnerId);
      setMallId(shopData.mallId);
    }
  }, [shopData]);

  const handleImageChange = (event) => {
    const file = event.target.files[0];
    if (file && file.type.startsWith('image/')) {
      setSelectedImage(file);
      setError(null);
    } else {
      setError('Please select a valid image file.');
    }
  };

  const handleSubmit = () => {
    if (!shopName || !shopStatus || !shopOwnerId || !mallId) {
      setError('Please fill all the fields.');
      return;
    }
    const formData = new FormData();
    formData.append('shopName', shopName);
    formData.append('shopStatus', shopStatus);
    formData.append('shopOwnerId', shopOwnerId);
    formData.append('mallId', mallId);
    if (selectedImage) {
      formData.append('image', selectedImage);
    }
    onSubmit(formData, shopData?.shopId || null); // Pass shopId if updating
  };

  return (
    <Dialog open={open} onClose={onClose}>
      <DialogTitle>{shopData ? 'Update Shop' : 'Add Shop'}</DialogTitle>
      <DialogContent>
        {error && (
          <Snackbar open={Boolean(error)} autoHideDuration={6000} onClose={() => setError(null)}>
            <Alert severity="error" onClose={() => setError(null)}>
              {error}
            </Alert>
          </Snackbar>
        )}
        <TextField
          label="Shop Name"
          fullWidth
          value={shopName}
          onChange={(e) => setShopName(e.target.value)}
          margin="normal"
        />
        <TextField
          label="Shop Status"
          fullWidth
          value={shopStatus}
          onChange={(e) => setShopStatus(e.target.value)}
          margin="normal"
        />
        <TextField
          label="Shop Owner ID"
          fullWidth
          value={shopOwnerId}
          onChange={(e) => setShopOwnerId(e.target.value)}
          margin="normal"
        />
        <TextField
          label="Mall ID"
          fullWidth
          value={mallId}
          onChange={(e) => setMallId(e.target.value)}
          margin="normal"
        />
        <input type="file" accept="image/*" onChange={handleImageChange} style={{ marginTop: '16px' }} />
      </DialogContent>
      <DialogActions>
        <Button onClick={onClose} color="primary">
          Cancel
        </Button>
        <Button onClick={handleSubmit} color="primary">
          {shopData ? 'Update' : 'Add'}
        </Button>
      </DialogActions>
    </Dialog>
  );
};

export default ShopImageDialog;
