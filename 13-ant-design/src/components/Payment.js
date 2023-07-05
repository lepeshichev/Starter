import React from 'react';
import { Button } from 'antd';
import { useDispatch } from 'react-redux';

const Payment = () => {
    const dispatch = useDispatch();

    const handlePay = () => {
    };

    return (
        <>
            <Button  onClick={handlePay} style={{marginRight: '10px', color: 'red'}}>Тут был платеж</Button>
        </>
    );
};

export default Payment;