import axios from 'axios';

// Function to delete an employee by employeeId
const deleteEmployee = async (employeeId) => {
  try {
    await axios.delete(`/employees/${employeeId}`);
    console.log(`Employee ${employeeId} deleted successfully.`);
    return true;
  } catch (error) {
    console.error("Error deleting employee:", error);
    throw new Error(error.response?.data?.message || error.message);
  }
};

// Function to delete an item by itemId
const deleteItem = async (itemId) => {
  try {
    await axios.delete(`/items/${itemId}`);
    console.log(`Item ${itemId} deleted successfully.`);
    return true;
  } catch (error) {
    console.error("Error deleting item:", error);
    throw new Error(error.response?.data?.message || error.message);
  }
};

// Function to delete a shop by shopId
const deleteShop = async (shopId) => {
  try {
    await axios.delete(`/shops/${shopId}`);
    console.log(`Shop ${shopId} deleted successfully.`);
    return true;
  } catch (error) {
    console.error("Error deleting shop:", error);
    throw new Error(error.response?.data?.message || error.message);
  }
};

// Function to get shop details, including employees and items
const getShopDetails = async (shopId) => {
  try {
    const response = await axios.get(`/shops/${shopId}`);
    return response.data; // Assuming the response contains shop details
  } catch (error) {
    console.error("Error fetching shop details:", error);
    throw new Error(error.response?.data?.message || error.message);
  }
};

// Function to delete a shop along with its employees and items
const deleteShopWithEmployeesAndItems = async (shopId) => {
  try {
    // Step 1: Fetch the shop details, including employees and items
    const shopDetails = await getShopDetails(shopId);
    const employees = shopDetails.employees;
    const items = shopDetails.items;

    console.log(`Deleting shop ${shopId} with employees and items`);

    // Step 2: Delete all employees of the shop
    for (const employee of employees) {
      await deleteEmployee(employee.employeeId);
    }

    // Step 3: Delete all items of the shop
    for (const item of items) {
      await deleteItem(item.itemId);
    }

    // Step 4: Delete the shop itself
    await deleteShop(shopId);
    console.log(`Shop ${shopId}, along with its employees and items, has been deleted successfully.`);
    
    return true; // Return true on successful operation
  } catch (error) {
    console.error("Error deleting shop, employees, or items:", error);
    throw new Error(error.response?.data?.message || error.message);
  }
};

// Function to delete a mall by mallId
const deleteMall = async (mallId) => {
  try {
    await axios.delete(`/malls/${mallId}`);
    console.log(`Mall ${mallId} deleted successfully.`);
    return true;
  } catch (error) {
    console.error("Error deleting mall:", error);
    throw new Error(error.response?.data?.message || error.message);
  }
};

// Function to get the mall details, including the list of shops
const getMallDetails = async (mallId) => {
  try {
    const response = await axios.get(`/malls/${mallId}`);
    return response.data; // Assuming the response contains mall details with shops
  } catch (error) {
    console.error("Error fetching mall details:", error);
    throw new Error(error.response?.data?.message || error.message);
  }
};

// Function to delete a mall along with its shops, employees, and items
const deleteMallWithShopsAndItemsAndEmployees = async (mallId) => {
  try {
    // Step 1: Fetch the mall details including the list of shops
    const mallDetails = await getMallDetails(mallId);
    const shops = mallDetails.shops;

    console.log(`Deleting mall ${mallId} with shops, employees, and items`);

    // Step 2: For each shop, delete the shop along with its employees and items
    for (const shop of shops) {
      await deleteShopWithEmployeesAndItems(shop.shopId);
    }

    // Step 3: Delete the mall itself
    await deleteMall(mallId);
    console.log(`Mall ${mallId}, along with its shops, employees, and items, has been deleted successfully.`);
    
    return true; // Return true on successful operation
  } catch (error) {
    console.error("Error deleting mall, shops, employees, or items:", error);
    throw new Error(error.response?.data?.message || error.message);
  }
};

// Export all functions so that each can be used individually
export { 
  deleteEmployee, 
  deleteItem, 
  deleteShop, 
  getShopDetails, 
  deleteShopWithEmployeesAndItems, 
  deleteMall, 
  getMallDetails, 
  deleteMallWithShopsAndItemsAndEmployees 
};
