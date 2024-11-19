// src/Components/Header.jsx
import React, { useContext, useState } from 'react';
import {
  AppBar,
  Toolbar,
  Typography,
  Button,
  Box,
  IconButton,
  Drawer,
  List,
  ListItem,
  ListItemText,
  useMediaQuery,
} from '@mui/material';
import { Link, useNavigate, useLocation } from 'react-router-dom';
import MenuIcon from '@mui/icons-material/Menu';
import logo from '../pics/logoonlyss.png'; // Adjust path as needed
import { AuthContext } from './ContextAPI/AuthContext.js'; // Import AuthContext

const Header = () => {
  const { isLoggedIn, logout } = useContext(AuthContext); // Use context
  const navigate = useNavigate();
  const location = useLocation();
  const isMobile = useMediaQuery('(max-width:768px)');
  const [isDrawerOpen, setIsDrawerOpen] = useState(false);

  const handleDrawerToggle = () => {
    setIsDrawerOpen(!isDrawerOpen);
  };

  const handleNavigate = (path) => {
    setIsDrawerOpen(false);
    navigate(path);
  };

  const handleLogout = () => {
    logout(); // Call the logout function from context
    navigate('/');
  };

  const isActive = (path) => location.pathname === path;

  return (
    <AppBar position="static" sx={{ backgroundColor: 'white', boxShadow: '0 2px 4px rgba(0, 0, 0, 0.1)' }}>
      <Toolbar sx={{ display: 'flex', justifyContent: 'space-between', py: 1 }}>
        <Box sx={{ display: 'flex', alignItems: 'center' }}>
          <img src={logo} alt="Logo" width="40" height="40" style={{ borderRadius: '50%', border: '2px solid #e0e0e0' }} />
          <Typography
            variant="h6"
            sx={{
              marginLeft: 1,
              color: 'black',
              fontWeight: 'bold',
              letterSpacing: '0.5px',
              '&:hover': { color: '#007bff', cursor: 'pointer' },
            }}
            onClick={() => handleNavigate('/')}
          >
            Shoppers'STOP
          </Typography>
        </Box>

        {!isMobile && (
          <Box sx={{ display: 'flex', alignItems: 'center', gap: 4 }}>
            <Button component={Link} to="/" sx={{ color: isActive('/') ? '#007bff' : '#6c757d', textTransform: 'none', fontWeight: 'bold', '&:hover': { color: '#007bff' } }}>
              Home
            </Button>
            <Button component={Link} to="/faqs" sx={{ color: isActive('/faqs') ? '#007bff' : '#6c757d', textTransform: 'none', fontWeight: 'bold', '&:hover': { color: '#007bff' } }}>
              FAQs
            </Button>
            <Button component={Link} to="/about" sx={{ color: isActive('/about') ? '#007bff' : '#6c757d', textTransform: 'none', fontWeight: 'bold', '&:hover': { color: '#007bff' } }}>
              About
            </Button>
            <Button component={Link} to="/contact" sx={{ color: isActive('/contact') ? '#007bff' : '#6c757d', textTransform: 'none', fontWeight: 'bold', '&:hover': { color: '#007bff' } }}>
              Contact Us
            </Button>

            {isLoggedIn ? (
              <>
                <Button component={Link} to="/profile" sx={{ color: '#6c757d', textTransform: 'none', fontWeight: 'bold', '&:hover': { color: '#007bff' } }}>
                  Profile
                </Button>
                <Button component={Link} to="/account" sx={{ color: '#6c757d', textTransform: 'none', fontWeight: 'bold', '&:hover': { color: '#007bff' } }}>
                  My Account
                </Button>
                <button
  className="bg-blue-500 text-white font-bold rounded-md px-4 py-2"
  onClick={handleLogout}
>
  Logout
</button>




              </>
            ) : (
              <Link to="/login" className="inline-block">
              <button className="font-bold text-white bg-blue-500 hover:bg-blue-600 px-4 py-2 rounded">
                  Login/Register
              </button>
          </Link>




            )}
          </Box>
        )}

        {isMobile && (
          <IconButton onClick={handleDrawerToggle}>
            <MenuIcon fontSize="large" sx={{ color: 'black' }} />
          </IconButton>
        )}
      </Toolbar>

      <Drawer anchor="right" open={isDrawerOpen} onClose={handleDrawerToggle}>
        <List sx={{ width: 250 }}>
          <ListItem button onClick={() => handleNavigate('/')}>
            <ListItemText primary="Home" primaryTypographyProps={{ fontWeight: 'bold' }} />
          </ListItem>
          <ListItem button onClick={() => handleNavigate('/faqs')}>
            <ListItemText primary="FAQs" primaryTypographyProps={{ fontWeight: 'bold' }} />
          </ListItem>
          <ListItem button onClick={() => handleNavigate('/about')}>
            <ListItemText primary="About" primaryTypographyProps={{ fontWeight: 'bold' }} />
          </ListItem>
          <ListItem button onClick={() => handleNavigate('/contact')}>
            <ListItemText primary="Contact Us" primaryTypographyProps={{ fontWeight: 'bold' }} />
          </ListItem>

          {isLoggedIn ? (
            <>
              <ListItem button onClick={() => handleNavigate('/profile')}>
                <ListItemText primary="Profile" primaryTypographyProps={{ fontWeight: 'bold' }} />
              </ListItem>
              <ListItem button onClick={() => handleNavigate('/account')}>
                <ListItemText primary="My Account" primaryTypographyProps={{ fontWeight: 'bold' }} />
              </ListItem>
              <ListItem button onClick={handleLogout}>
                <ListItemText primary="Logout" primaryTypographyProps={{ fontWeight: 'bold' }} />
              </ListItem>
            </>
          ) : (
            <ListItem button onClick={() => handleNavigate('/login')}>
              <ListItemText primary="Login/Register" primaryTypographyProps={{ fontWeight: 'bold' }} />
            </ListItem>
          )}
        </List>
      </Drawer>
    </AppBar>
  );
};

export default Header;
