import axios from 'axios';

const API_URL = 'http://localhost:8080';

export const fetchProducts = async () => {
    try {
        return await axios.get(`${API_URL}/products/all`);
    } catch (error) {
        console.error('Error fetching products', error);
        throw error;
    }
};

export const fetchProductById = async (id) => {
    try {
        return await axios.get(`${API_URL}/products/${id}`);
    } catch (error) {
        console.error('Error fetching product by ID', error);
        throw error;
    }
};