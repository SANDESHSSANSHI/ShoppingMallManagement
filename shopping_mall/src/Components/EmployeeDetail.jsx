import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Container, Typography, CircularProgress, Button, Snackbar, Alert } from '@mui/material';
import { useParams, useNavigate } from 'react-router-dom';

const EmployeeDetail = () => {
  const { employeeId } = useParams(); // Get employeeId from URL
  const [employee, setEmployee] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchEmployeeDetails = async () => {
      setLoading(true);
      try {
        const response = await axios.get(`/employees/${employeeId}`); // Fetch employee details using employeeId
        setEmployee(response.data);
      } catch (err) {
        setError(err.response?.data?.message || err.message);
      } finally {
        setLoading(false);
      }
    };
    fetchEmployeeDetails();
  }, [employeeId]);

  const handleCloseSnackbar = () => {
    setError(null);
  };

  if (loading) return <CircularProgress />;

  return (
    <Container className="max-w-2xl mx-auto py-8 px-4 bg-white shadow-md rounded-lg">
      {error && (
        <Snackbar open={Boolean(error)} autoHideDuration={6000} onClose={handleCloseSnackbar}>
          <Alert onClose={handleCloseSnackbar} severity="error" sx={{ width: '100%' }}>
            {error}
          </Alert>
        </Snackbar>
      )}
      {employee ? (
        <>
          <Typography variant="h4" className="text-center text-2xl font-bold text-gray-800 mb-4">{employee.employeeName}</Typography>
          <div className="bg-gray-100 p-4 rounded-md mb-6">
            <Typography variant="body1" className="text-gray-700"><strong>Designation:</strong> {employee.employeeDesignation}</Typography>
            <Typography variant="body1" className="text-gray-700"><strong>Date of Birth:</strong> {employee.employeeDateOfBirth}</Typography>
            <Typography variant="body1" className="text-gray-700"><strong>Salary:</strong> ₹{employee.employeeSalary}/-</Typography>
            <Typography variant="body1" className="text-gray-700"><strong>Address:</strong> {employee.employeeAddress}</Typography>
            {/* Add other employee details as needed */}
          </div>
          <Button 
            variant="contained" 
            className="w-full bg-blue-500 text-white py-2 rounded-md hover:bg-blue-600 transition duration-300"
            onClick={() => navigate(-1)}
          >
            Back to Employee List
          </Button>
        </>
      ) : (
        <Typography variant="body1" className="text-center text-gray-600">No employee details available.</Typography>
      )}
    </Container>
  );
};

export default EmployeeDetail;
