import {Space, Table} from 'antd';
import {useDispatch, useSelector} from "react-redux";

const ProductsCartTable = () => {
    const products1 = useSelector((state) => state.products.products)
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
    ];

    return (
        <Table rowKey="id"  columns={columns} dataSource={products1} />
    );
}
export default ProductsCartTable;