import { configureStore } from '@reduxjs/toolkit';
import booksReducer from "./slices/ProductsSlice";
import cartReducer from "./slices/CartSlice";

export default configureStore({
    reducer: {
        products: booksReducer,
        cart: cartReducer,
    },
});