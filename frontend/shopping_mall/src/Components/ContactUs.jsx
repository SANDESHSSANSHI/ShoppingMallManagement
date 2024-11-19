import React from 'react';
import { Typography, Container, Box } from '@mui/material';

const ContactUs = () => {
  return (
    <Container maxWidth="sm" style={{ marginTop: '40px' }}>
      <Typography variant="h4" gutterBottom>
        Contact Us
      </Typography>

      {/* Contact Information */}
      <Box sx={{ marginBottom: '20px' }}>
        <Typography variant="h6">Our Address:</Typography>
        <Typography variant="body1">
          Don Bosco Institute of Technology, Mysore Road, Kumbalagodu, Bangalore - 560074
        </Typography>

        <Typography variant="h6" style={{ marginTop: '10px' }}>Email:</Typography>
        <Typography variant="body1">support@dbitmall.com</Typography>

        <Typography variant="h6" style={{ marginTop: '10px' }}>Phone:</Typography>
        <Typography variant="body1">+91 9876543210</Typography>
      </Box>
    </Container>
  );
};

export default ContactUs;
