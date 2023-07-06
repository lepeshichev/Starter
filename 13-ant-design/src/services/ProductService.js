import axios from "axios";
import {add} from "../slices/ProductsSlice";

const API_URL = "http://localhost:8081/products";

const getProducts = (dispatch) => {
    return axios.get(API_URL).then(
        (response) => {
            dispatch(add(response.data));
        },
        (error) => {
            const _content = (error.response && error.response.data) ||
                error.message ||
                error.toString();
            console.error(_content)
            dispatch(add([]));
        });

};

const createProduct = (product, dispatch) => {
    return axios.post(API_URL, product).then(
        (response) => {
            getProducts(dispatch)
        },
        (error) => {
            const _content = (error.response && error.response.data) ||
                error.message ||
                error.toString();
            console.error(_content)
        });
};

const deleteProduct = (id, dispatch) => {
    return axios.delete(API_URL + `/${id}`).then(
        (response) => {
            getProducts(dispatch)
        },
        (error) => {
            const _content = (error.response && error.response.data) ||
                error.message ||
                error.toString();

            console.error(_content)
        });
};

const ProductService = {
    getProducts,
    createProduct,
    deleteProduct,
};

export default ProductService