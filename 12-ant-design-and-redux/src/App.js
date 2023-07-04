import './App.css';
import {Layout} from "antd";
import {Content, Header} from "antd/es/layout/layout";
import ProductsTable from "./components/ProductsTable";
import Search from "./components/Search";
import CreateProductForm from "./components/CreateProductForm";
import {useState} from "react";
import {Profile} from "./components/Profile";
import ProductsCartTable from "./components/ProductsCartTable";

function App() {
  return (
      <Layout className="layout">
        <Header style={{display: 'flex', alignItems: 'center', justifyContent: 'space-between'}}>
          <h1 style={{color: "white"}}>Магазин продуктов </h1>
            <h1 style={{color: "white"}}><Profile/> </h1>
          <Search style={{marginLeft: 'auto'}}/>
        </Header>
        <Content style={{padding: '0 50px'}}>
          <CreateProductForm />
          <div className="site-layout-content">
            <ProductsTable/>
          </div>
            <h1>Корзина:</h1>
            <div className="site-layout-content">
                <ProductsCartTable/>
            </div>
        </Content>
      </Layout>
  );
}

export default App;
