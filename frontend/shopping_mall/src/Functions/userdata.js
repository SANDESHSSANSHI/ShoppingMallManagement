// userdata.js

// User object to manage user data and login status
let user = {
  isLoggedIn: false,
};

// Function to set user data and update login status
export const setuser = (data) => {
  user = { ...user, ...data, isLoggedIn: true }; // Automatically log in user when data is set
  localStorage.setItem('isLoggedIn', true); // Set login state in local storage
  console.log("User data updated: ", user);
};

// Function to get user data and check login status
export const getuser = () => {
  const loggedIn = localStorage.getItem('isLoggedIn') === 'true'; // Check login state
  console.log("Fetching user data: ", { isLoggedIn: loggedIn });
  return { ...user, isLoggedIn: loggedIn }; // Return current user state
};

// Function to log out the user
export const logout = () => {
  user.isLoggedIn = false; // Log out the user
  localStorage.removeItem('isLoggedIn'); // Remove login state from local storage
  console.log("User logged out");
};

