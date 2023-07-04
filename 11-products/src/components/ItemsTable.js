export const ItemsTable = ({
                               items,
                               removeItem,
                               increaseCount,
                               decreaseCount
                           }) => (
    <table className="price-table">
        <thead>
        <tr>
            <th>Наименование товара</th>
            <th>Цена за шт</th>
            <th>Кол-во</th>
            <th>Цена</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        {items.map((item) => (
            <tr key={item.id}>
                <td>{item.name}</td>
                <td>{item.price}</td>
                <td>
                    <button onClick={() => decreaseCount(item.id, item.count)}>
                        -
                    </button>
                    <span className="count">{item.count}</span>
                    <button onClick={() => increaseCount(item.id)}>+</button>
                </td>
                <td>{item.price * item.count}</td>
                <td>
                    <button onClick={() => removeItem(item.id)}>Х</button>
                </td>
            </tr>
        ))}
        </tbody>
    </table>
);
