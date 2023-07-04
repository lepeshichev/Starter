import {createSlice} from '@reduxjs/toolkit'

export const productsSlice = createSlice({
    name: 'products',
    initialState: {
        products: [
            {
                id: '1',
                name: 'Форель',
                price: '100',
                amount: 15,
            },
            {
                id: '2',
                name: 'Окунь',
                price: '200',
                amount: 10,
            },
            {
                id: '3',
                name: 'Осетр',
                price: '300',
                amount: 5,
            },
        ],
    },
    reducers: {
        push: (state, action) => {
            const product = action.payload;
            product.id = Math.floor(Math.random() * 1_000_000);
            state.products = [action.payload, ...state.products]
        },
        remove: (state, action) => {
            state.products = state.products.filter(product => product.id !== action.payload.id)
        },
        add: (state, action) => {

        },
    },
})

// Action creators are generated for each case reducer function
export const {add, push, remove} = productsSlice.actions

export default productsSlice.reducer