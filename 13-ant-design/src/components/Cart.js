import React from 'react';
import { Card, Button } from 'antd';
import { useDispatch, useSelector } from 'react-redux';
import { removeFromCart } from '../slices/CartSlice';
import Payment from './Payment';

const Cart = () => {
    const dispatch = useDispatch();
    const cart = useSelector((state) => state.cart);

    const handleRemoveFromCart = (productId) => {
        dispatch(removeFromCart({ productId }));
    };

    return (
        <div>
            <h2>Корзина</h2>
            {cart.products.length === 0 ? (
                <h2>Тут пока пусто =(</h2>
            ) : (
                <div>
                    {cart.products.map((product) => (
                        <Card
                            key={product.id}
                            title={product.name}
                            extra={<Button  onClick={() => handleRemoveFromCart(product.id)}>Удалить</Button>}
                        >
                            <p>Цена: {product.price}</p>
                            <p>Количество: {product.quantity}</p>
                        </Card>
                    ))}
                    <p>Общая стоимость: {cart.total}</p>
                        <Payment />
                </div>
            )}
        </div>
    );
};

export default Cart;