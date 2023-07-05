import {Button, Form, Input, InputNumber, Select} from 'antd';
import {useDispatch, useSelector} from "react-redux";
import {add} from "../slices/ProductsSlice";

const {Option} = Select;

const CreateProductForm = () => {
    const dispatch = useDispatch()
    const [form] = Form.useForm();

    const onFinish = (values) => {
        dispatch(add(values));
    };

    const onReset = () => {
        form.resetFields();
    };

    return (
        <Form
            form={form}
            name="control-hooks"
            onFinish={onFinish}
            style={{
                maxWidth: 450,
                padding: '30px 30px 20px 0',

            }}
        >
            <h2 style={{
                textAlign: 'center'
            }}
            >Добавление продукта</h2>
            <Form.Item
                name="name"
                label="Название"
                rules={[
                    {
                        required: true,
                    },
                ]}
            >
                <Input/>
            </Form.Item>



            <Form.Item
                name="quantity"
                label="Количество"
                rules={[
                    {
                        required: true,
                    },
                ]}
            >
                <InputNumber min='0'/>
            </Form.Item>

            <Form.Item
                name="price"
                label="Стоимость"
                rules={[
                    {
                        required: true,
                    },
                ]}
            >
                <InputNumber min='0'/>
            </Form.Item>

            <Form.Item>
                <Button  type="primary" htmlType="submit" style={{
                    marginRight: '10px'
                }}>
                    Создать
                </Button>
                <Button  htmlType="button" onClick={onReset}>
                    Очистить
                </Button>

            </Form.Item>
        </Form>
    );
};
export default CreateProductForm;