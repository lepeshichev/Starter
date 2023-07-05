import { createSlice } from "@reduxjs/toolkit";

export const productsSlice = createSlice({
    name: "products",
    initialState: {
        products: [
            /*
            {
                id: "1",
                name: "Форель",
                quantity: 15,
                price: 100,
            },
            {
                id: "2",
                name: "Окунь",
                quantity: 10,
                price: 2000,
            },
            {
                id: "3",
                name: "Сом",
                quantity: 1,
                price: 450,
            },
             */
        ],
        filteredProducts: [],
    },
    reducers: {
        editProduct: (state, action) => {
            const { id, name, quantity, price } = action.payload;
            const product = state.products.find((product) => product.id === id);
            if (product) {
                product.name = name;
                product.quantity = quantity;
                product.price = price;
            }
        },

        remove: (state, action) => {
            const { id } = action.payload;
            state.products = state.products.filter((product) => product.id !== id);
        },
        add: (state, action) => {
            const { name, quantity, price } = action.payload;
            const id = Math.floor(Math.random() * 1_000_000);
            const newProduct = {
                id: id.toString(),
                name,
                quantity,
                price,
            };
            state.products.push(newProduct);
        },
        setFilteredProducts: (state, action) => {
            state.filteredProducts = action.payload;
        },
    },
});

export const {remove, add} = productsSlice.actions;

export default productsSlice.reducer;