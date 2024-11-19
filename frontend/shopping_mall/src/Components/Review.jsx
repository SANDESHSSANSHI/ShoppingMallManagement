import React from 'react';
import img1 from "./static/images/site_requirred/footer/person_1.jpg";
import img2 from "./static/images/site_requirred/footer/person_2.jpg";
import img3 from "./static/images/site_requirred/footer/person_3.jpg";
import img4 from "./static/images/site_requirred/footer/person_4.jpg";

const reviews = [
  {
    id: 1,
    name: "Jane Doe",
    role: "Verified Buyer",
    content: "I absolutely love this product! The quality exceeded my expectations, and the customer service was fantastic. I would highly recommend this to everyone.",
    image: img1,
    date: "October 28, 2024",
  },
  {
    id: 2,
    name: "John Smith",
    role: "Verified Buyer",
    content: "Amazing product! It has changed my life for the better. Excellent quality and quick delivery.",
    image: img2,
    date: "October 29, 2024",
  },
  {
    id: 3,
    name: "Alice Johnson",
    role: "Verified Buyer",
    content: "Highly satisfied with my purchase! The customer service team was very helpful and responsive.",
    image: img3,
    date: "October 30, 2024",
  },
  {
    id: 4,
    name: "Bob Brown",
    role: "Verified Buyer",
    content: "Great value for money! I would definitely buy again and recommend to friends.",
    image: img4,
    date: "October 31, 2024",
  },
];

const Review = () => {
  return (
    <div className="flex justify-center overflow-x-auto space-x-6 py-6">
      {reviews.map((review) => (
        <div
          key={review.id}
          className="flex flex-col items-center bg-white p-6 rounded-lg shadow-lg max-w-xs transition-transform transform hover:scale-105 hover:shadow-2xl hover:bg-gray-50"
        >
          <div className="flex items-center space-x-4">
            <img
              src={review.image}
              alt={review.name}
              className="w-16 h-16 rounded-full border-2 border-indigo-500 transition-transform transform hover:scale-110"
            />
            <div>
              <h3 className="text-lg font-semibold text-gray-800">{review.name}</h3>
              <p className="text-sm text-gray-500">{review.role}</p>
            </div>
          </div>

          <div className="text-center mt-4">
            <p className="text-gray-700 text-sm sm:text-base leading-relaxed">
              “{review.content}”
            </p>
          </div>

          <div className="flex space-x-1 mt-2">
            {[...Array(5)].map((_, i) => (
              <svg
                key={i}
                xmlns="http://www.w3.org/2000/svg"
                viewBox="0 0 20 20"
                fill="currentColor"
                className="w-6 h-6 text-yellow-400 transition-transform transform hover:scale-110"
              >
                <path d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.436 4.408a1 1 0 00.95.69h4.631c.969 0 1.371 1.24.588 1.81l-3.753 2.723a1 1 0 00-.364 1.118l1.437 4.408c.3.921-.755 1.688-1.54 1.118L10 15.347l-3.753 2.723c-.784.57-1.838-.197-1.539-1.118l1.437-4.408a1 1 0 00-.364-1.118L2.029 9.835c-.784-.57-.38-1.81.589-1.81h4.631a1 1 0 00.95-.69L9.049 2.927z" />
              </svg>
            ))}
          </div>

          <div className="text-center mt-2">
            <p className="text-xs text-gray-500">Reviewed on {review.date}</p>
          </div>
        </div>
      ))}
    </div>
  );
};

export default Review;
