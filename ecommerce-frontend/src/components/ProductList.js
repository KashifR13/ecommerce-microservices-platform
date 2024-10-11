import React, {useEffect, useState} from 'react';
import {fetchProducts} from '../services/apiService';

const ProductList = () => {
    const [products, setProducts] = useState([]);

    useEffect(() => {
        const getProducts = async () => {
            try {
                const response = await fetchProducts();
                console.log(response.data);
                setProducts(response.data);
            } catch (error) {
                console.error('Error fetching products', error);
            }
        };

        getProducts().then(r => console.log('Products fetched'));
    }, []);

    return (
        <div>
            <h1>Product List</h1>
            <ul>
                {products.map(product => (
                    <li key={product.id}>{product.name} - ${product.price}</li>
                ))}
            </ul>
        </div>
    );
};

export default ProductList;