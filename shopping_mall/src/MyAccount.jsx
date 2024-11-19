// MyAccount.jsx
import React from 'react';
import { Box, Typography, Paper } from '@mui/material';

const MyAccount = () => {
  return (
    <Box
      sx={{
        padding: 4,
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center',
        minHeight: '100vh',
        background: 'linear-gradient(135deg, #ece9e6 0%, #ffffff 100%)',
      }}
    >
      <Paper
        elevation={6}
        sx={{
          padding: 4,
          width: '100%',
          maxWidth: '500px',
          textAlign: 'center',
          borderRadius: 2,
          backgroundColor: '#f0f0f0',
        }}
      >
        <Typography variant="h4" gutterBottom sx={{ fontWeight: 'bold', color: '#1976d2' }}>
          My Account
        </Typography>
        <Typography variant="h6" sx={{ marginBottom: 2 }}>
          Manage your account settings here.
        </Typography>
        {/* Add any additional account management details here */}
      </Paper>
    </Box>
  );
};

export default MyAccount;
