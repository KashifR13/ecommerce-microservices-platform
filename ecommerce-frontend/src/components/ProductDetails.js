import React, {useEffect, useState} from 'react';
import {fetchProductById} from '../services/apiService';
import {useParams} from 'react-router-dom';

const ProductDetail = () => {
    const {id} = useParams();
    const [product, setProduct] = useState(null);

    useEffect(() => {
        const fetchProduct = async () => {
            try {
                const response = await fetchProductById(id);
                console.log(response);
                setProduct(response.data);
            } catch (error) {
                console.error('Error fetching product', error);
            }
        };

        fetchProduct().then(r => console.log('Product fetched'));
    }, [id]);

    if (!product) return <div>Loading...</div>;

    return (
        <div>
            <h1>{product.name}</h1>
            <p>{product.description}</p>
            <p>${product.price}</p>
        </div>
    );
};

export default ProductDetail;