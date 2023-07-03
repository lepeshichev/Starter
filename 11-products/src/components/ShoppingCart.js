import {useState} from "react";
import {defaultItems} from "./Products.js";
import {ItemsTable} from "./ItemsTable.js";

export function ShoppingCart() {
    const [items, setItems] = useState(defaultItems);
    const result = items.reduce(
        (previousValue, currentItem) =>
            previousValue + currentItem.price * currentItem.count,
        0
    );
    const EmptyTemplate = (
        <div className="empty-text">У вас нет еще товаров в корзине</div>
    );
    const Footer = (
        <div className="result-panel">
      <span>
        Общая стоимость: <span className="value">{result} р.</span>
      </span>
            <button onClick={() => handlePayCart()}>
                Оформить
            </button>
        </div>
    );

    const handlePayCart = () => {
        alert("Вы оплатили")
    };


    const handleRemoveItem = (id) => {
        setItems(items.filter((item) => item.id !== id));
    };

    const handleIncreaseCount = (id) => {
        setItems(
            items.map((item) => {
                if (item.id === id) {
                    item.count++;
                }
                return item;
            })
        );
    };

    const handleDecreaseCount = (id, count) => {
        if (count < 2) {
            handleRemoveItem(id);
        } else {
            setItems(
                items.map((item) => {
                    if (item.id === id) {
                        item.count--;
                    }
                    return item;
                })
            );
        }
    };
    return (
        <>
            <h1>Сформируйте вашу корзину:</h1>
            {items.length ? (
                <ItemsTable
                    items={items}
                    removeItem={handleRemoveItem}
                    increaseCount={handleIncreaseCount}
                    decreaseCount={handleDecreaseCount}
                />
            ) : (
                EmptyTemplate
            )}
            {!!items.length && Footer}
        </>
    );
}
