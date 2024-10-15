import React, {useState} from 'react';
import axios from 'axios';

const Checkout = () => {
    const [address, setAddress] = useState('');
    const [paymentMethod, setPaymentMethod] = useState('');

    const handleCheckout = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post('/checkout', {address, paymentMethod});
            console.log(response.data);
        } catch (error) {
            console.error('Error during checkout', error);
        }
    };

    return (
        <form onSubmit={handleCheckout}>
            <div>
                <label>Address:</label>
                <input type="text" value={address} onChange={(e) => setAddress(e.target.value)}/>
            </div>
            <div>
                <label>Payment Method:</label>
                <input type="text" value={paymentMethod} onChange={(e) => setPaymentMethod(e.target.value)}/>
            </div>
            <button type="submit">Checkout</button>
        </form>
    );
};

export default Checkout;