import React, { useState, useEffect } from 'react';

// Sample slide data
const slides = [
    {
        productImage: 'https://cmsimages.shoppersstop.com/stop_web_379cc2f147/stop_web_379cc2f147.png',
        logo: 'https://cmsimages.shoppersstop.com/stop_Logo_Web_124ffe3df5/stop_Logo_Web_124ffe3df5.png',
        discount: 'UP TO 50% OFF',
    },
    {
        productImage: 'https://cmsimages.shoppersstop.com/Fratini_web_31c3e69368/Fratini_web_31c3e69368.png',
        logo: 'https://cmsimages.shoppersstop.com/Fratini_Logo_Web_82f52fb4bb/Fratini_Logo_Web_82f52fb4bb.png',
        discount: 'UP TO 50% OFF',
    },
    {
        productImage: 'https://cmsimages.shoppersstop.com/KASHISH_web_06d1e80ed4/KASHISH_web_06d1e80ed4.png',
        logo: 'https://cmsimages.shoppersstop.com/Kashish_01_Logo_Web_bf7c96404b/Kashish_01_Logo_Web_bf7c96404b.png',
        discount: 'UP TO 50% OFF',
    },
    {
        productImage: 'https://cmsimages.shoppersstop.com/Life_web_394338632e/Life_web_394338632e.png',
        logo: 'https://cmsimages.shoppersstop.com/life_Logo_Web_4d23b56cbe/life_Logo_Web_4d23b56cbe.png',
        discount: 'UP TO 50% OFF',
    },
    {
        productImage: 'https://cmsimages.shoppersstop.com/uryou_web_e5dfd0c364/uryou_web_e5dfd0c364.png',
        logo: 'https://cmsimages.shoppersstop.com/U_Ryou_Logo_Web_d74fb009f2/U_Ryou_Logo_Web_d74fb009f2.png',
        discount: 'UP TO 50% OFF',
    },
];

const Slides = () => {
    const [currentIndex, setCurrentIndex] = useState(0);

    // Automatically go to the next slide every 2 seconds
    useEffect(() => {
        const interval = setInterval(() => {
            setCurrentIndex((prevIndex) => (prevIndex + 1) % slides.length);
        }, 2000); // 2000 ms = 2 seconds

        return () => clearInterval(interval); // Clear the interval on component unmount
    }, []);

    const getSlideStyle = (index) => {
        if (index === currentIndex) {
            return 'translate-x-[-50%] scale-100 filter-none opacity-100 z-20';
        } else if (index === (currentIndex - 1 + slides.length) % slides.length) {
            return 'translate-x-[-160%] scale-90 grayscale opacity-50 z-10';
        } else if (index === (currentIndex + 1) % slides.length) {
            return 'translate-x-[60%] scale-90 grayscale opacity-50 z-10';
        } else {
            return 'hidden';
        }
    };

    return (
        <div className="flex flex-col items-center h-[80vh] w-full bg-black overflow-hidden"> {/* Set bg to black */}
            <h1 className="mt-6 text-3xl font-extrabold text-gray-200 text-center mb-2 font-sans shadow-md"> 
    50% OFF!
</h1>
<h2 className="mt-4 text-xl font-bold text-gray-300 text-center mb-5 font-sans shadow-md"> 
    Refresh Your Style with Limited-Time Offers!
</h2>



            <div className="relative flex justify-center items-center w-[961.594px] h-[500px]">
                {slides.map((slide, index) => (
                    <div
                        key={index}
                        className={`absolute left-1/2 transition-transform duration-450 ease-in-out ${getSlideStyle(index)}`}
                    >
                        <div className="relative bg-gradient-to-t from-gray-900 border-2 border-white rounded-lg h-[480px] w-[320px]">
                            <img
                                alt="brand_product"
                                src={slide.productImage}
                                className="w-full h-full rounded-lg object-cover"
                                loading="lazy"
                            />
                            <div className="absolute bottom-1.5 w-full flex flex-col items-center px-6">
                                <img
                                    alt="brand_logo"
                                    src={slide.logo}
                                    className="mb-1.5 px-7"
                                    loading="lazy"
                                />
                                <div className="mb-1 text-center text-lg font-bold text-white">
                                    {slide.discount}
                                </div>
                            </div>
                        </div>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default Slides;
