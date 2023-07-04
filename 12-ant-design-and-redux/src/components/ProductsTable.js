import {Space, Table} from 'antd';
import {useDispatch, useSelector} from "react-redux";
import {remove} from "../slices/productsSlice";
import {add} from "../slices/productsSlice";

const ProductsTable = () => {
    const products = useSelector((state) => state.products.products)
    const dispatch = useDispatch()

    const columns = [
        {
            title: 'Название',
            dataIndex: 'name',
            key: 'name',
            render: (text) => <a>{text}</a>,
        },
        {
          title: 'Цена',
          dataIndex: 'price',
          key: 'price',
        },
        {
            title: 'Количество',
            dataIndex: 'amount',
            key: 'amount',
        },
        {
          title: 'Добавить в корзину',
            key: 'add',
            render: (_, product) => (
                <Space size="middle" onClick={() => dispatch(add(product))}>
                    <a>Добавить</a>
                </Space>
            ),
        },
        {
            title: 'Удалить продукт',
            key: 'delete',
            render: (_, product) => (
                <Space size="middle" onClick={() => dispatch(remove(product))}>
                    <a>Удалить</a>
                </Space>
            ),
        },
    ];

    return (
        <Table rowKey="id"  columns={columns} dataSource={products} />
    );
}
export default ProductsTable;