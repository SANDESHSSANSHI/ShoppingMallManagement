// import React, { useState } from 'react';
// import { Button, Dialog, DialogActions, DialogContent, DialogContentText, DialogTitle, TextField, Typography } from '@mui/material';
// import axios from 'axios';

// const Payment = ({ orderDetails, onPaymentSuccess }) => {
//   const [open, setOpen] = useState(false);
//   const [paymentMode, setPaymentMode] = useState('Cash on Delivery');
//   const [dateOfPurchase, setDateOfPurchase] = useState('');
//   const [loading, setLoading] = useState(false);

//   const handleOpen = () => {
//     setOpen(true);
//   };

//   const handleClose = () => {
//     setOpen(false);
//   };

//   const handleConfirmPayment = async () => {
//     setLoading(true);
//     try {
//       const paymentData = {
//         orderId: orderDetails.orderId,
//         dateOfPurchase: dateOfPurchase,
//         paymentMode: paymentMode,
//       };

//       // Send the payment data to the backend
//       await axios.post('/payments', paymentData);
//       onPaymentSuccess(); // Callback to indicate successful payment
//     } catch (error) {
//       console.error('Error processing payment:', error);
//       alert('Failed to process payment. Please try again.');
//     } finally {
//       setLoading(false);
//       handleClose();
//     }
//   };

//   return (
//     <div>
//       <Button onClick={handleOpen}>Proceed to Payment</Button>

//       <Dialog open={open} onClose={handleClose}>
//         <DialogTitle>Payment Confirmation</DialogTitle>
//         <DialogContent>
//           <DialogContentText>
//             Please enter the date of purchase and select the payment mode.
//           </DialogContentText>
//           <TextField
//             autoFocus
//             margin="dense"
//             label="Date of Purchase"
//             type="date"
//             fullWidth
//             value={dateOfPurchase}
//             onChange={(e) => setDateOfPurchase(e.target.value)}
//             InputLabelProps={{
//               shrink: true,
//             }}
//           />
//           <TextField
//             select
//             margin="dense"
//             label="Payment Mode"
//             fullWidth
//             value={paymentMode}
//             onChange={(e) => setPaymentMode(e.target.value)}
//             className="mt-4"
//           >
//             <option value="Cash on Delivery">Cash on Delivery</option>
//             <option value="Online">Online</option>
//           </TextField>
//         </DialogContent>
//         <DialogActions>
//           <Button onClick={handleClose}>Cancel</Button>
//           <Button onClick={handleConfirmPayment} disabled={loading}>
//             {loading ? 'Processing...' : 'Confirm Payment'}
//           </Button>
//         </DialogActions>
//       </Dialog>
//     </div>
//   );
// };

// export default Payment;
