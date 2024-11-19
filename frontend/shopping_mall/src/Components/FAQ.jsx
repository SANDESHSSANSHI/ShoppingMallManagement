// src/Components/FAQ.jsx
import React from 'react';
import { Box, Typography, Accordion, AccordionSummary, AccordionDetails } from '@mui/material';
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';

const FAQ = () => {
  const faqs = [
    {
      question: "What is the purpose of this application?",
      answer: "This application is designed to help users manage their shopping experiences efficiently, providing features like browsing malls, shops, and items."
    },
    {
      question: "How do I reset my password?",
      answer: "To reset your password, click on the 'Forgot Password?' link on the login page and follow the instructions sent to your email."
    },
    {
      question: "Can I access my account from multiple devices?",
      answer: "Yes, you can access your account from multiple devices. Just log in with your credentials on any device."
    },
    {
      question: "Who can I contact for support?",
      answer: "You can contact our support team via the 'Contact Us' page or send an email to support@example.com."
    },
    {
      question: "Is my data secure?",
      answer: "Yes, we take data security very seriously. All user data is encrypted and stored securely."
    },
  ];

  return (
    <Box sx={{ padding: 3 }}>
      <Typography variant="h4" gutterBottom>
        Frequently Asked Questions
      </Typography>
      {faqs.map((faq, index) => (
        <Accordion key={index}>
          <AccordionSummary
            expandIcon={<ExpandMoreIcon />}
            aria-controls={`panel${index + 1}a-content`}
            id={`panel${index + 1}a-header`}
          >
            <Typography variant="h6">{faq.question}</Typography>
          </AccordionSummary>
          <AccordionDetails>
            <Typography>
              {faq.answer}
            </Typography>
          </AccordionDetails>
        </Accordion>
      ))}
    </Box>
  );
};

export default FAQ;
