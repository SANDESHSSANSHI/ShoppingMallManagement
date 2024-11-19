import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { getuser } from '../Functions/userdata';
import { useNavigate } from 'react-router-dom';
import { deleteMallWithShopsAndItemsAndEmployees } from './Delete/delete';
import { Dialog, DialogActions, DialogContent, DialogTitle, Button, TextField } from '@mui/material';

const MallsList = () => {
  const { type, id, name } = getuser(); // Fetch user details
  const [malls, setMalls] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [dialogOpen, setDialogOpen] = useState(false); // Dialog state
  const [updateDialogOpen, setUpdateDialogOpen] = useState(false); // Update dialog state
  const [selectedMallId, setSelectedMallId] = useState(null); // For tracking the mall being updated
  const [newMall, setNewMall] = useState({
    mallId: '',
    mallName: '',
    mallLocation: '',
    mallCategory: '',
  });
  const [snackbarOpen, setSnackbarOpen] = useState(false);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchMalls = async () => {
      try {
        const response = await axios.get('/malls');
        setMalls(response.data);
      } catch (error) {
        setError(error.message);
      } finally {
        setLoading(false);
      }
    };

    fetchMalls();
  }, [newMall]);

  const handleDialogOpen = () => {
    setDialogOpen(true);
  };

  const handleDialogClose = () => {
    setDialogOpen(false);
  };

  const handleUpdateDialogOpen = (mall) => {
    // Populate the dialog with selected mall data
    setNewMall({
      mallId: mall.mallId,
      mallName: mall.mallName,
      mallLocation: mall.mallLocation,
      mallCategory: mall.mallCategory,
    });
    setSelectedMallId(mall.mallId); // Set the selected mall's ID for updating
    setUpdateDialogOpen(true);
  };

  const handleUpdateDialogClose = () => {
    setUpdateDialogOpen(false);
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setNewMall((prevMall) => ({ ...prevMall, [name]: value }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const mallData = { ...newMall, mallAdminId: id }; // Set mallAdminId to current user id
      const response = await axios.post('/malls', mallData);
      setMalls((prevMalls) => [...prevMalls, response.data]);
      setNewMall({
        mallId: '',
        mallName: '',
        mallLocation: '',
        mallCategory: '',
      });
      setDialogOpen(false); // Close dialog after submission
      setSnackbarOpen(true);
    } catch (error) {
      setError(error.message);
    }
  };

  const handleMallClick = (mallId) => {
    navigate(`/malls/${mallId}?type=${type}&id=${id}`);
  };

  const handleDelete = async (mallId) => {
    const confirmDelete = window.confirm(
      'Are you sure you want to delete this mall and all its shops, employees, and items?'
    );
    if (confirmDelete) {
      try {
        await deleteMallWithShopsAndItemsAndEmployees(mallId);
        setMalls((prevMalls) => prevMalls.filter((mall) => mall.mallId !== mallId));
        setSnackbarOpen(true);
      } catch (error) {
        setError(error.message);
      }
    }
  };

  const handleUpdateSubmit = async (e) => {
    e.preventDefault();
    try {
      const mallData = {
        mallName: newMall.mallName,
        mallAdminId: id,
        mallLocation: newMall.mallLocation,
        mallCategory: newMall.mallCategory,
      };

      await axios.put(`/malls/${selectedMallId}`, mallData); // PUT request to update mall
      setMalls((prevMalls) =>
        prevMalls.map((mall) =>
          mall.mallId === selectedMallId ? { ...mall, ...mallData } : mall
        )
      );
      setUpdateDialogOpen(false); // Close dialog after update
      setSnackbarOpen(true);
    } catch (error) {
      setError(error.message);
    }
  };

  if (loading)
    return (
      <div className="flex justify-center items-center ">
        <div className="spinner-border animate-spin"></div>
      </div>
    );

  return (
    <div className="w-full  p-6 flex flex-col">
      <header className="bg-blue-600 py-4 w-full">
        <div className=" mx-auto flex justify-between items-center px-4">
          <h1 className="text-white text-xl font-bold">
            {type}: {name}
          </h1>
        </div>
      </header>

      <div className="bg-white flex flex-col justify-center items-center p-6 mt-6 rounded-lg shadow-lg w-full flex-grow">
        <h2 className="text-2xl font-semibold mb-6">Malls List</h2>

        {error && (
          <div className="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded relative mb-4">
            Error: {error}
          </div>
        )}

        {/* Grid for Malls List */}
        <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-4 justify-center w-full">
          {malls.map((mall) => (
            <div key={mall.mallId} className="p-6 bg-gray-100 rounded-lg shadow-md flex flex-col">
              <h3 className="text-xl font-semibold mb-2">{mall.mallName}</h3>
              {/* <p className="text-gray-700">Mall ID: {mall.mallId}</p> */}
              <p className="text-gray-700">Location: {mall.mallLocation}</p>
              <p className="text-gray-700">Category: {mall.mallCategory}</p>
              <button
                className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded mt-4 w-full"
                onClick={() => handleMallClick(mall.mallId)}
              >
                View Shops
              </button>

              {id === mall.mallAdminId && (
                <>
                  <button
                    className="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded mt-2 w-full"
                    onClick={() => handleDelete(mall.mallId)}
                  >
                    Delete
                  </button>
                  <button
                    className="bg-yellow-500 hover:bg-yellow-700 text-white font-bold py-2 px-4 rounded mt-2 w-full"
                    onClick={() => handleUpdateDialogOpen(mall)}
                  >
                    Update
                  </button>
                </>
              )}
            </div>
          ))}
        </div>
      </div>

      {/* Button for adding new mall */}
      {type === 'MallAdmin' && (
        <div className="mt-8 w-full flex justify-center">
          <button
            className="bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-4 rounded"
            onClick={handleDialogOpen}
          >
            Add New Mall
          </button>
        </div>
      )}

      {/* Dialog for adding new mall */}
      <Dialog open={dialogOpen} onClose={handleDialogClose}>
        <DialogTitle>Add New Mall</DialogTitle>
        <DialogContent>
          <form onSubmit={handleSubmit} className="flex flex-col gap-4">
            <TextField
              label="Mall ID"
              type="number"
              name="mallId"
              value={newMall.mallId}
              onChange={handleChange}
              required
              fullWidth
            />
            <TextField
              label="Mall Name"
              type="text"
              name="mallName"
              value={newMall.mallName}
              onChange={handleChange}
              required
              fullWidth
            />
            <TextField
              label="Location"
              type="text"
              name="mallLocation"
              value={newMall.mallLocation}
              onChange={handleChange}
              required
              fullWidth
            />
            <TextField
              label="Category"
              type="text"
              name="mallCategory"
              value={newMall.mallCategory}
              onChange={handleChange}
              required
              fullWidth
            />
          </form>
        </DialogContent>
        <DialogActions>
          <Button onClick={handleDialogClose} color="secondary">
            Cancel
          </Button>
          <Button onClick={handleSubmit} color="primary">
            Add Mall
          </Button>
        </DialogActions>
      </Dialog>

      {/* Dialog for updating mall */}
      <Dialog open={updateDialogOpen} onClose={handleUpdateDialogClose}>
        <DialogTitle>Update Mall</DialogTitle>
        <DialogContent>
          <form onSubmit={handleUpdateSubmit} className="flex flex-col gap-4">
            <TextField
              label="Mall Name"
              type="text"
              name="mallName"
              value={newMall.mallName}
              onChange={handleChange}
              required
              fullWidth
            />
            <TextField
              label="Location"
              type="text"
              name="mallLocation"
              value={newMall.mallLocation}
              onChange={handleChange}
              required
              fullWidth
            />
            <TextField
              label="Category"
              type="text"
              name="mallCategory"
              value={newMall.mallCategory}
              onChange={handleChange}
              required
              fullWidth
            />
          </form>
        </DialogContent>
        <DialogActions>
          <Button onClick={handleUpdateDialogClose} color="secondary">
            Cancel
          </Button>
          <Button onClick={handleUpdateSubmit} color="primary">
            Update Mall
          </Button>
        </DialogActions>
      </Dialog>

      {snackbarOpen && (
        <div className="fixed bottom-0 right-0 mb-4 mr-4 bg-green-500 text-white py-2 px-4 rounded shadow-lg">
          Operation completed successfully!
        </div>
      )}
    </div>
  );
};

export default MallsList;
