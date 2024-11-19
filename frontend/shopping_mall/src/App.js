// src/App.jsx
import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import {
  Login,
  Customer,
  Mall,
  ShopList,
  Items,
  EmployeeList,
  EmployeeDetail,
  FAQ,
  About,
  Profile,
  Order,
  MyAccount,
  MyOrder, // Import MyOrder component
  NotFound,
  ContactUs,
  Home,
} from './Components/index.js'; // Adjust according to your structure
import Layout from './Components/Layout'; // Adjust based on your layout setup
import { AuthProvider } from './Components/ContextAPI/AuthContext.js'; // Import AuthProvider

function App() {
  return (
    <AuthProvider>
      <Router>
        <Routes>
          <Route path="/" element={<Layout />}>
            <Route path="" element={<Home />} />
            <Route path="/login" element={<Login />} />
            <Route path="/customer" element={<Customer />} />
            <Route path="/mall/:type" element={<Mall />} />
            <Route path="/malls/:mallId" element={<ShopList />} />
            <Route path="/shops/:shopId" element={<Items />} />
            <Route path="/employees/:shopId" element={<EmployeeList />} />
            <Route path="/employees/details/:employeeId" element={<EmployeeDetail />} />
            <Route path="/about" element={<About />} />
            <Route path="/faqs" element={<FAQ />} />
            <Route path="/profile" element={<Profile />} />
            <Route path="/account" element={<MyAccount />} />
            <Route path="/contact" element={<ContactUs />} />
            <Route path="/order/:cartId" element={<Order />} />
            <Route path="/my-orders" element={<MyOrder />} /> {/* New route for MyOrder */}
            <Route path="*" element={<NotFound />} /> {/* 404 Not Found */}
          </Route>
        </Routes>
      </Router>
    </AuthProvider>
  );
}

export default App;
