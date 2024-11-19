import React from 'react';
import Header from './Header';
import Footer from './Footer';
import { Outlet } from 'react-router-dom';

const Layout = () => {
  return (
    <div className="flex flex-col justify-between h-[100vh]">
      {/* Fixed Header */}
      <Header />
      
      {/* Page content */}
<Outlet/>     

      {/* Fixed Footer */}
      
      <Footer />
    </div>
  );
};

export default Layout;
