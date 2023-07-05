import React from 'react';
import { Button } from 'antd';
import { useDispatch } from 'react-redux';
import { addToCart } from '../slices/CartSlice';

const AddToCartButton = ({ product }) => {
    const dispatch = useDispatch();

    const handleAddToCart = () => {
        dispatch(addToCart({ product }));
    };

    return (
        <Button type="primary" onClick={handleAddToCart}>Добавить в корзину</Button>
    );
};

export default AddToCartButton;