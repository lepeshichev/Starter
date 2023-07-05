
import React, { useState } from "react";
import { Dropdown, Menu } from "antd";
import { Link } from "react-router-dom";

const MainMenu = () => {

    const handleMenuClick = (e) => {
    };

    const menu = (
        <Menu onClick={handleMenuClick}>
            <Menu.Item key="products">
                <Link to="/products">Товары</Link>
            </Menu.Item>
            <Menu.Item key="cart">
                <Link to="/cart">
                    Корзина
                </Link>
            </Menu.Item>
        </Menu>
    );

    return (
        <>
            <Dropdown overlay={menu} trigger={["click"]}>
                <h1 style={{ color: "white", cursor: "pointer"}}> Меню </h1>
            </Dropdown>
        </>
    );
};

export default MainMenu;