// src/Components/About.jsx
import React from 'react';
import { Container, Typography, Box } from '@mui/material';

const About = () => {
  return (
    <Container maxWidth="md" sx={{ mt: 4, mb: 4 }}>
      <Typography variant="h4" component="h1" gutterBottom>
        About Shoppers' STOP
      </Typography>
      <Box sx={{ mt: 2 }}>
        <Typography variant="body1" paragraph>
          Shoppers' STOP is your one-stop destination for all your shopping needs. We bring you a wide range of products across various categories to ensure you have everything you need at your fingertips.
        </Typography>
        <Typography variant="body1" paragraph>
          Our mission is to provide a seamless shopping experience, whether you are looking for the latest fashion trends, electronics, or home essentials. With an intuitive user interface and advanced features, Shoppers' STOP makes online shopping convenient and enjoyable.
        </Typography>
        <Typography variant="body1" paragraph>
          We are committed to customer satisfaction and constantly strive to enhance our platform by incorporating user feedback and the latest technologies.
        </Typography>
        <Typography variant="body1" paragraph>
          Thank you for choosing Shoppers' STOP. Happy shopping!
        </Typography>
      </Box>
    </Container>
  );
};

export default About;
