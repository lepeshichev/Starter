import { AutoComplete, Input } from 'antd';
import React from 'react';
import { useSelector } from "react-redux";
const Search = ({ handleSearch }) => {
    const products = useSelector((state) => state.products.products);
    const searchResult = (query) => {
        return products
            //.filter(product => product.name.toLowerCase().includes(query.toLowerCase()))
            .map(product => {
                return {
                    value: product.id,
                    label: <div key={product.id}>{product.name}</div>
                }
            });
    }
    return (
        <AutoComplete
            popupMatchSelectWidth={252}
            style={{
                width: 300,
            }}
            options={searchResult('')}
            onSearch={handleSearch}
        >
            <Input.Search size="large" placeholder="Введите название продукта" enterButton />
        </AutoComplete>
    );
};
export default Search;