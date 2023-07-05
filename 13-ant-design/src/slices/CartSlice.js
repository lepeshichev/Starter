import {createSlice} from '@reduxjs/toolkit';

const CartSlice = createSlice({
    name: 'cart',
    initialState: {
        products: [],
        total: 0,
        paymentStatus: null,
    },
    reducers: {
        addToCart: (state, action) => {
            const {product} = action.payload;
            const existingProduct = state.products.find((p) => p.id === product.id);

            if (existingProduct) {
                existingProduct.quantity += 1;
            } else {
                state.products.push({...product, quantity: 1});
            }
            state.total += product.price;
        },
        updateQuantity: (state, action) => {
            const {productId, quantity} = action.payload;
            const product = state.products.find((p) => p.id === productId);
            if (product) {
                state.total -= product.price * product.quantity;
                product.quantity = quantity;
                state.total += product.price * product.quantity;
            }
        },
        removeFromCart: (state, action) => {
            const {productId} = action.payload;
            const productIndex = state.products.findIndex((p) => p.id === productId);
            if (productIndex !== -1) {
                const product = state.products[productIndex];
                state.total -= product.price * product.quantity;
                state.products.splice(productIndex, 1);
            }
        },
        pay: (state, action) => {
            const productsInCart = state.products;
            const productsInStore = action.payload;

            for (const productInCart of productsInCart) {
                const productInStore = productsInStore.find((p) => p.id === productInCart.id);
                productInStore.quantity -= productInCart.quantity;
                state.products = [];
                state.total = 0;
            }
        },
        resetPaymentStatus: (state) => {
            state.paymentStatus = null;
        },
        updateProductName: (state, action) => {
            const {productId, name} = action.payload;
            const product = state.products.find((p) => p.id === productId);
            if (product) {
                product.name = name;
            }
            // Обновление названия продукта в массиве filteredBooks
            state.filteredBooks = state.filteredBooks.map((product) => {
                if (product.id === productId) {
                    return {...product, name};
                }
                return product;
            });
        },
    },
});

export const {
    addToCart,
    removeFromCart,
    pay,
} = CartSlice.actions;

export default CartSlice.reducer;