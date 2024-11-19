import React, { useEffect, useState } from 'react';
import {
  Typography,
  Button,
  CircularProgress,
  Snackbar,
  Alert,
  Dialog,
  DialogTitle,
  DialogContent,
  DialogActions,
  TextField,
} from '@mui/material';
import { useParams, useNavigate } from 'react-router-dom';
import axios from 'axios';
import { getuser } from '../Functions/userdata';
import { deleteShopWithEmployeesAndItems } from '../Components/Delete/delete';

const ShopList = () => {
  const { type, id } = getuser();
  const { mallId } = useParams();
  
  const navigate = useNavigate();
  const [shops, setShops] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [deleteLoading, setDeleteLoading] = useState(false);
  const [successMessage, setSuccessMessage] = useState(null);
  const [openAddDialog, setOpenAddDialog] = useState(false);
  const [openUpdateDialog, setOpenUpdateDialog] = useState(false);
  const [shopData, setShopData] = useState({
    shopId: '',
    shopName: '',
    shopStatus: '',
    shopOwnerId: id,
    mallId: mallId,
    image: null,
  });

  useEffect(() => {
    const fetchShops = async () => {
      setLoading(true);
      try {
        const response = await axios.get(`/malls/${mallId}`);
        setShops(response.data.shops);
      } catch (err) {
        setError(err.response?.data?.message || err.message);
      } finally {
        setLoading(false);
      }
    };
    fetchShops();
  }, [mallId, id]);

  const handleCloseSnackbar = () => setError(null);

  const handleDeleteShop = async (shopId, shopOwnerId) => {
    if (shopOwnerId === id) {
      setDeleteLoading(true);
      try {
        await deleteShopWithEmployeesAndItems(shopId);
        setSuccessMessage(`Shop ${shopId} deleted successfully.`);
        const response = await axios.get(`/malls/${mallId}`);
        setShops(response.data.shops);
      } catch (err) {
        setError(err.message);
      } finally {
        setDeleteLoading(false);
      }
    }
  };

  const handleOpenAddDialog = () => {
    setShopData({
      shopId: '',
      shopName: '',
      shopStatus: '',
      shopOwnerId: id,
      mallId: mallId,
      image: null,
    });
    setOpenAddDialog(true);
  };

  const handleOpenUpdateDialog = (shop) => {
    setShopData({
      shopId: shop.shopId,
      shopName: shop.shopName,
      shopStatus: shop.shopStatus,
      shopOwnerId: shop.shopOwnerId,
      mallId: mallId,
      image: null,
    });
    setOpenUpdateDialog(true);
  };

  const handleCloseAddDialog = () => setOpenAddDialog(false);
  const handleCloseUpdateDialog = () => setOpenUpdateDialog(false);

  const handleImageChange = (event) => {
    const file = event.target.files[0];
    if (file) {
      setShopData((prev) => ({
        ...prev,
        image: file,
      }));
    }
  };

  const handleAddShop = async () => {
    try {
      const { shopId, shopName, shopStatus, shopOwnerId, mallId, image } = shopData;
      const formData = new FormData();
      formData.append('shopId', shopId);
      formData.append('shopName', shopName);
      formData.append('shopStatus', shopStatus);
      formData.append('shopOwnerId', shopOwnerId);
      formData.append('mallId', mallId);
      if (image) {
        formData.append('image', image);
      }

      await axios.post('/shops', formData, {
        headers: {
          'Content-Type': 'multipart/form-data',
        },
      });
      
      setSuccessMessage(`Shop ${shopName} added successfully.`);
      handleCloseAddDialog();
      
      const response = await axios.get(`/malls/${mallId}`);
      setShops(response.data.shops);
      
    } catch (err) {
      setError(err.response?.data?.message || err.message);
    }
  };

  const handleUpdateShop = async () => {
    try {
      const { shopId, shopName, shopStatus, shopOwnerId, mallId, image } = shopData;
      const formData = new FormData();
      formData.append('shopName', shopName);
      formData.append('shopStatus', shopStatus);
      formData.append('shopOwnerId', shopOwnerId);
      formData.append('mallId', mallId);
      if (image) {
        formData.append('image', image);
      }

      await axios.put(`/shops/${shopId}`, formData, {
        headers: {
          'Content-Type': 'multipart/form-data',
        },
      });
      
      setSuccessMessage(`Shop ${shopId} updated successfully.`);
      handleCloseUpdateDialog();
      
      const response = await axios.get(`/malls/${mallId}`);
      setShops(response.data.shops);
      
    } catch (err) {
      setError(err.response?.data?.message || err.message);
    }
  };

  if (loading) return <div className="flex justify-center items-center h-screen"><CircularProgress /></div>;

  return (
    <div className="w-11/12 mx-auto">
      <Typography variant="h4" className="text-2xl bg-blue-200 p-4 rounded shadow-md hover:bg-blue-300 transition duration-300 ease-in-out"
      >
        Shops
      </Typography>

      {error && (
        <Snackbar open={Boolean(error)} autoHideDuration={6000} onClose={handleCloseSnackbar}>
          <Alert onClose={handleCloseSnackbar} severity="error" className="w-full">
            Error: {error}
          </Alert>
        </Snackbar>
      )}

      {type === "ShopOwner" && (
        <Button variant="contained" color="primary" className="mb-6" onClick={handleOpenAddDialog}>
          Add Shop
        </Button>
      )}

      {successMessage && (
        <Snackbar open={Boolean(successMessage)} autoHideDuration={6000} onClose={() => setSuccessMessage(null)}>
          <Alert onClose={() => setSuccessMessage(null)} severity="success" className="w-full">
            {successMessage}
          </Alert>
        </Snackbar>
      )}

      <div className="grid gap-3 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-3">
        {shops.length > 0 ? (
          shops.map((shop) => (
            <div key={shop.shopId} className="bg-white p-3 shadow-lg rounded-lg flex flex-col items-center space-y-4">
              <Typography variant="h5" className="font-semibold text-gray-800">{shop.shopName}</Typography>
              <Typography variant="subtitle1" className="text-gray-500">Owner ID: {shop.shopOwnerId}</Typography>
              <img
                src={`data:image/jpeg;base64,${shop.image}`}
                alt={shop.shopName}
                className="w-24 h-24 object-cover rounded-lg hover:shadow-md transition duration-300"
              />
              <Button
                variant="contained"
                color="primary"
                className="w-full mt-2"
                onClick={() => navigate(`/shops/${shop.shopId}?type=${type}&id=${id}&shopOwnerId=${shop.shopOwnerId}&shopName=${shop.shopName}`)}
              >
                View Items
              </Button>
              <Button
                variant="contained"
                color="secondary"
                className="w-full"
                onClick={() => navigate(`/employees/${shop.shopId}?type=${type}&id=${id}&shopOwnerId=${shop.shopOwnerId}&shopName=${shop.shopName}`)}
              >
                View Employees
              </Button>
              {shop.shopOwnerId === id && (
                <>
                  <Button
                    variant="contained"
                    color="secondary"
                    className="w-full"
                    onClick={() => handleOpenUpdateDialog(shop)}
                  >
                    Update
                  </Button>
                  <Button
                    variant="contained"
                    color="error"
                    className="w-full"
                    onClick={() => handleDeleteShop(shop.shopId, shop.shopOwnerId)}
                    disabled={deleteLoading}
                  >
                    {deleteLoading ? "Deleting..." : "Delete"}
                  </Button>
                </>
              )}
            </div>
          ))
        ) : (
          <Typography variant="h6" className="col-span-full text-center">No shops found for this mall.</Typography>
        )}
      </div>

      {/* Add Shop Dialog */}
      <Dialog open={openAddDialog} onClose={handleCloseAddDialog}>
        <DialogTitle>Add Shop</DialogTitle>
        <DialogContent>
          <TextField
            autoFocus
            margin="dense"
            label="Shop ID"
            type="text"
            fullWidth
            variant="outlined"
            value={shopData.shopId}
            onChange={(e) => setShopData(prev => ({ ...prev, shopId: e.target.value }))}
          />
          <TextField
            margin="dense"
            label="Shop Name"
            type="text"
            fullWidth
            variant="outlined"
            value={shopData.shopName}
            onChange={(e) => setShopData(prev => ({ ...prev, shopName: e.target.value }))}
          />
          <TextField
            margin="dense"
            label="Shop Status"
            type="text"
            fullWidth
            variant="outlined"
            value={shopData.shopStatus}
            onChange={(e) => setShopData(prev => ({ ...prev, shopStatus: e.target.value }))}
          />
          <input
            type="file"
            onChange={handleImageChange}
            className="mt-4"
          />
        </DialogContent>
        <DialogActions>
          <Button onClick={handleCloseAddDialog} color="primary">Cancel</Button>
          <Button onClick={handleAddShop} color="primary">Add Shop</Button>
        </DialogActions>
      </Dialog>

      {/* Update Shop Dialog */}
      <Dialog open={openUpdateDialog} onClose={handleCloseUpdateDialog}>
        <DialogTitle>Update Shop</DialogTitle>
        <DialogContent>
          <TextField
            autoFocus
            margin="dense"
            label="Shop Name"
            type="text"
            fullWidth
            variant="outlined"
            value={shopData.shopName}
            onChange={(e) => setShopData(prev => ({ ...prev, shopName: e.target.value }))}
          />
          <TextField
            margin="dense"
            label="Shop Status"
            type="text"
            fullWidth
            variant="outlined"
            value={shopData.shopStatus}
            onChange={(e) => setShopData(prev => ({ ...prev, shopStatus: e.target.value }))}
          />
          <input
            type="file"
            onChange={handleImageChange}
            className="mt-4"
          />
        </DialogContent>
        <DialogActions>
          <Button onClick={handleCloseUpdateDialog} color="primary">Cancel</Button>
          <Button onClick={handleUpdateShop} color="primary">Update Shop</Button>
        </DialogActions>
      </Dialog>
    </div>
  );
};

export default ShopList;
