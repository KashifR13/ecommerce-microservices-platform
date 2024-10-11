import axios from 'axios';

const API_URL = 'http://localhost:8080';

export const register = async (data) => {
    try {
        return await axios.post(`${API_URL}/users/register`, {data});
    } catch (error) {
        console.error('Error registering', error);
        throw error;
    }
};

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