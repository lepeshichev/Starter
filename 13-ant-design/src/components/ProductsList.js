import React, { useState, useEffect } from "react";
import { Space, Table } from "antd";
import { useDispatch, useSelector } from "react-redux";
import { remove } from "../slices/ProductsSlice";
import { removeFromCart } from "../slices/CartSlice";
import AddToCartButton from "./AddToCartButton";
import ProductService from "../services/ProductService";

const ProductsList = () => {
    const products = useSelector((state) => state.products.products);
    const dispatch = useDispatch();
    const [filteredProducts, setFilteredProducts] = useState([]);
    const [editingProduct, setEditingProduct] = useState(null);


    const handleRemoveFromCart = (product) => {
        dispatch(remove(product));
        dispatch(removeFromCart({ productId: product.id }));
    };

    useEffect(() => {
        getProducts();
    }, []);

    const getProducts = () => {
        ProductService.getProducts(dispatch);
    }

    const columns = [
        {
            title: "Наименование",
            dataIndex: "name",
            key: "name",
            render: (text) => <a>{text}</a>,
        },
        {
            title: "Стоимость",
            dataIndex: "price",
            key: "price",
        },
        {
            title: "Количество",
            dataIndex: "quantity",
            key: "quantity",
        },
        {
            title: "Действия",
            key: "actions",
            render: (_, product) => (
                <Space size="middle">
                    <AddToCartButton product={product} />
                    <a onClick={() => handleRemoveFromCart(product)}>Удалить</a>
                </Space>
            ),
        },
    ];

    return (
        <div>
            <Table rowKey="id" columns={columns} dataSource={products} />
            {editingProduct}
        </div>
    );
};

export default ProductsList;