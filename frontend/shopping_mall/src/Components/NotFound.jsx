import React from 'react';
import { Typography, Container } from '@mui/material';

const NotFound = () => {
  return (
    <Container style={{ marginTop: '20px', textAlign: 'center' }}>
      <Typography variant="h4" gutterBottom>
        404 - Page Not Found
      </Typography>
      <Typography variant="body1">
        Sorry, the page you are looking for does not exist.
      </Typography>
    </Container>
  );
};

export default NotFound;
