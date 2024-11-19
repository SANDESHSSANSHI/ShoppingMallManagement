import React, { useEffect, useState } from 'react';
import axios from 'axios';
import {
  AppBar,
  Toolbar,
  Typography,
  Container,
  CircularProgress,
  Snackbar,
  Alert,
  Grid,
  Paper,
  Button,
  TextField,
  Dialog,
  DialogActions,
  DialogContent,
  DialogTitle,
} from '@mui/material';
import { useParams, useLocation, useNavigate } from 'react-router-dom';
import { getuser } from '../Functions/userdata';
import { deleteEmployee } from './Delete/delete';

const EmployeeList = () => {
  const { shopId } = useParams();
  const location = useLocation();
  const navigate = useNavigate();
  const queryParams = new URLSearchParams(location.search);
  const shopOwnerId = queryParams.get("shopOwnerId");
  const { id } = getuser(); // Get the current user's ID

  const [employees, setEmployees] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [snackbarOpen, setSnackbarOpen] = useState(false);
  const [snackbarMessage, setSnackbarMessage] = useState('');
  const [snackbarSeverity, setSnackbarSeverity] = useState('success');

  // State for the dialog (add or edit)
  const [openDialog, setOpenDialog] = useState(false);
  const [isEditMode, setIsEditMode] = useState(false);

  // State for the new employee form
  const [newEmployee, setNewEmployee] = useState({
    employeeId: '',
    employeeName: '',
    employeeDateOfBirth: '',
    employeeSalary: '',
    employeeAddress: '',
    employeeDesignation: '',
    shopId: shopId,
  });

  const [selectedEmployeeId, setSelectedEmployeeId] = useState(null);

  useEffect(() => {
    const fetchEmployees = async () => {
      setLoading(true);
      try {
        const response = await axios.get(`/shops/${shopId}`);
        setEmployees(response.data.employees);
      } catch (err) {
        setError(err.response?.data?.message || err.message);
      } finally {
        setLoading(false);
      }
    };
    fetchEmployees();
  }, [shopId]);

  const handleCloseSnackbar = () => {
    setSnackbarOpen(false);
    setError(null);
  };

  const generateUniqueEmployeeId = (length) => {
    const min = Math.pow(10, length - 1);
    const max = Math.pow(10, length) - 1;
    return Math.floor(Math.random() * (max - min + 1)) + min;
  };

  const handleDialogOpen = (employee = null) => {
    if (employee) {
      // Edit mode
      setIsEditMode(true);
      setSelectedEmployeeId(employee.employeeId);
      setNewEmployee({
        employeeId: employee.employeeId, // Set the ID for editing
        employeeName: employee.employeeName,
        employeeDateOfBirth: employee.employeeDateOfBirth,
        employeeSalary: employee.employeeSalary,
        employeeAddress: employee.employeeAddress,
        employeeDesignation: employee.employeeDesignation,
        shopId: shopId,
      });
    } else {
      // Add mode
      setIsEditMode(false);
      setSelectedEmployeeId(null);
      setNewEmployee({
        employeeId: generateUniqueEmployeeId(8), // Generate unique employee ID
        employeeName: '',
        employeeDateOfBirth: '',
        employeeSalary: '',
        employeeAddress: '',
        employeeDesignation: '',
        shopId: shopId,
      });
    }
    setOpenDialog(true);
  };

  const handleDialogClose = () => {
    setOpenDialog(false);
    setNewEmployee({
      employeeId: '',
      employeeName: '',
      employeeDateOfBirth: '',
      employeeSalary: '',
      employeeAddress: '',
      employeeDesignation: '',
      shopId: shopId,
    });
    setSelectedEmployeeId(null);
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setNewEmployee((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      if (isEditMode && selectedEmployeeId) {
        // PUT request to update employee details
        const response = await axios.put(`/employees/${selectedEmployeeId}`, newEmployee);
        
        // Update state with edited employee
        setEmployees((prevEmployees) => 
          prevEmployees.map(emp => 
            emp.employeeId == selectedEmployeeId ? { ...emp, ...response.data } : emp
          )
        );
        setSnackbarMessage('Employee updated successfully!');
      } else {
        // POST request to add new employee
        const response = await axios.post('/employees', newEmployee);
        
        // Add new employee to the state and reflect changes immediately
        setEmployees((prevEmployees) => [...prevEmployees, response.data]);
        setSnackbarMessage('Employee added successfully!');
      }
      setSnackbarSeverity('success');
      setSnackbarOpen(true);
      handleDialogClose();
    } catch (error) {
      setError(error.response?.data?.message || error.message);
      setSnackbarMessage('Failed to save employee!');
      setSnackbarSeverity('error');
      setSnackbarOpen(true);
    }
  };

  const handleDelete = async (employeeId) => {
    try {
      await deleteEmployee(employeeId, shopOwnerId, id);
      setEmployees((prevEmployees) => prevEmployees.filter(emp => emp.employeeId !== employeeId));
      setSnackbarMessage('Employee deleted successfully!');
      setSnackbarSeverity('success');
      setSnackbarOpen(true);
    } catch (error) {
      setError(error.message);
      setSnackbarMessage('Failed to delete employee!');
      setSnackbarSeverity('error');
      setSnackbarOpen(true);
    }
  };

  if (loading) return <Container><CircularProgress /> <Typography>Loading employees...</Typography></Container>;

  return (
    <Container>
      <AppBar position="static">
        <Toolbar>
          <Typography variant="h6">{`Employees in Shop ${shopId}`}</Typography>
        </Toolbar>
      </AppBar>

      <Paper style={{ padding: '20px', marginTop: '20px' }}>
        <Typography variant="h4" gutterBottom>
          List of Employees
        </Typography>

        {id == shopOwnerId && ( // Check if current user is the shop owner
          <Button variant="contained" color="primary" onClick={() => handleDialogOpen()} style={{ marginBottom: '20px' }}>
            Add Employee
          </Button>
        )}

        {error && (
          <Snackbar open={Boolean(error)} autoHideDuration={6000} onClose={handleCloseSnackbar}>
            <Alert onClose={handleCloseSnackbar} severity="error" sx={{ width: '100%' }}>
              {`Error: ${error}`}
            </Alert>
          </Snackbar>
        )}

        <Grid container spacing={2}>
          {employees.length > 0 ? (
            employees.map((employee) => (
              <Grid item xs={12} md={6} key={employee.employeeId}>
                <Paper style={{ padding: '15px', marginBottom: '15px' }}>
                  <Typography variant="h5">{employee.employeeName}</Typography>
                  <Typography variant="body1">Designation: {employee.employeeDesignation}</Typography>

                  <Button
                    variant="outlined"
                    color="primary"
                    onClick={() => navigate(`/employees/details/${employee.employeeId}`)}
                    style={{ marginTop: '10px' }}
                  >
                    View Employee Details
                  </Button>

                  {id == shopOwnerId && ( // Check if current user is the shop owner
                    <>
                      <Button
                        variant="contained"
                        color="primary"
                        onClick={() => handleDialogOpen(employee)}
                        style={{ marginTop: '10px' }}
                      >
                        Update Employee
                      </Button>

                      <Button
                        variant="outlined"
                        color="secondary"
                        onClick={() => handleDelete(employee.employeeId)}
                        style={{ marginLeft: '10px', marginTop: '10px' }}
                      >
                        Delete
                      </Button>
                    </>
                  )}
                </Paper>
              </Grid>
            ))
          ) : (
            <Typography variant="h6">No employees found for this shop.</Typography>
          )}
        </Grid>
      </Paper>

      <Snackbar open={snackbarOpen} autoHideDuration={6000} onClose={handleCloseSnackbar}>
        <Alert onClose={handleCloseSnackbar} severity={snackbarSeverity} sx={{ width: '100%' }}>
          {snackbarMessage}
        </Alert>
      </Snackbar>

      <Dialog open={openDialog} onClose={handleDialogClose}>
        <DialogTitle>{isEditMode ? 'Update Employee' : 'Add New Employee'}</DialogTitle>
        <DialogContent>
          <TextField
            margin="dense"
            label="Employee ID"
            type="text"
            name="employeeId"
            value={newEmployee.employeeId}
            onChange={handleInputChange}
            disabled={true} // Disable editing employee ID
            fullWidth
          />
          <TextField
            margin="dense"
            label="Name"
            type="text"
            name="employeeName"
            value={newEmployee.employeeName}
            onChange={handleInputChange}
            fullWidth
          />
          <TextField
            margin="dense"
            label="Date of Birth"
            type="date"
            name="employeeDateOfBirth"
            value={newEmployee.employeeDateOfBirth}
            onChange={handleInputChange}
            InputLabelProps={{ shrink: true }}
            fullWidth
          />
          <TextField
            margin="dense"
            label="Salary"
            type="number"
            name="employeeSalary"
            value={newEmployee.employeeSalary}
            onChange={handleInputChange}
            fullWidth
          />
          <TextField
            margin="dense"
            label="Address"
            type="text"
            name="employeeAddress"
            value={newEmployee.employeeAddress}
            onChange={handleInputChange}
            fullWidth
          />
          <TextField
            margin="dense"
            label="Designation"
            type="text"
            name="employeeDesignation"
            value={newEmployee.employeeDesignation}
            onChange={handleInputChange}
            fullWidth
          />
        </DialogContent>
        <DialogActions>
          <Button onClick={handleDialogClose} color="secondary">
            Cancel
          </Button>
          <Button onClick={handleSubmit} color="primary">
            {isEditMode ? 'Update' : 'Add'}
          </Button>
        </DialogActions>
      </Dialog>
    </Container>
  );
};

export default EmployeeList;
