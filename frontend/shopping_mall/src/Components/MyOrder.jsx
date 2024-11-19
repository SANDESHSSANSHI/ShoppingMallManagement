// src/Components/MyOrder.jsx
import React, { useEffect, useState } from 'react';
import { List, ListItem, ListItemText, Divider, Typography, CircularProgress, Button } from '@mui/material';
import { getuser } from '../Functions/userdata';

const MyOrder = () => {
    const [orders, setOrders] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    const { id } = getuser();
     useEffect(() => {
        const fetchOrders = async () => {
            console.log("id:", id);
            
            try {
                const response = await fetch(`/orderdetails/customer/${id}`, {
                    method: 'GET'
                });
                if (!response.ok) {
                    throw new Error('Failed to fetch order details');
                }
                const data = await response.json();
                setOrders(data);
            } catch (err) {
                setError(err.message);
            } finally {
                setLoading(false);
            }
        };
    
        fetchOrders();
    }, [id]);

// Delete order function
const deleteOrder = async (orderId) => {
    const confirmDelete = window.confirm("Are you sure you want to delete this order?");
    if (confirmDelete) {
        try {
            const response = await fetch(`/orderdetails/${orderId}`, {
                method: 'DELETE'
            });
            if (!response.ok) {
                throw new Error('Failed to delete order');
            }
            // Remove the deleted order from the list
            setOrders(orders.filter(order => order.orderId !== orderId));
        } catch (err) {
            setError(err.message);
        }
    }
};


    if (loading) {
        return <div className="flex justify-center items-center h-screen"><CircularProgress /></div>;
    }

    if (error) {
        return <div className="flex justify-center items-center h-screen text-red-500">{error}</div>;
    }

    return (
        <div className=" py-12 px-4">
        <div className="p-8 max-w-3xl mx-auto bg-gray-100 rounded-lg shadow-lg">
            <Typography variant="h4" className="text-blue-600 text-center mb-6 font-semibold">My Orders</Typography>
            {orders.length === 0 ? (
                <Typography variant="body1" className="text-center text-gray-600">No orders found.</Typography>
            ) : (
                <List>
                    {orders.map(order => (
                        <React.Fragment key={order.orderId}>
                            <ListItem className="bg-white rounded-md shadow p-4 mb-4">
                                <ListItemText
                                    primary={<span className="text-lg font-medium">Order ID: {order.orderId} - Total Price: ${order.totalPrice}</span>}
                                    secondary={
                                        <div>
                                            <Typography variant="body2" className="text-gray-700 mt-1">
                                                Mall: {order.mallName} | Shop: {order.shopName}
                                            </Typography>
                                            <Typography variant="body2" className="text-gray-700">
                                                Date: {order.dateOfPurchase} | Payment Mode: {order.paymentMode}
                                            </Typography>
                                            <Typography variant="body2" className="text-gray-700">
                                                Number of Items: {order.noOfItems}
                                            </Typography>
                                            <div className="mt-2">
                                                <Typography variant="body2" className="font-semibold">Item Details:</Typography>
                                                <ul className="pl-5 list-disc text-gray-600">
                                                    {order.itemDetails.map(item => (
                                                        <li key={item.itemId}>{item.itemName}</li>
                                                    ))}
                                                </ul>
                                            </div>
                                        </div>
                                    }
                                />
                                <Button 
                                    variant="contained" 
                                    color="secondary" 
                                    className="mt-4"
                                    onClick={() => deleteOrder(order.orderId)}
                                >
                                    Delete
                                </Button>
                            </ListItem>
                            <Divider />
                        </React.Fragment>
                    ))}
                </List>
            )}
        </div>
        </div>
    );
};

export default MyOrder;
