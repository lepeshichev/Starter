import React from "react";
import { Layout, Row, Col } from "antd";
import { Content, Header } from "antd/es/layout/layout";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import MainMenu from "./components/MainMenu";
import ProductsList from "./components/ProductsList";
import CreateProdutForm from "./components/CreateProdutForm";
import Cart from "./components/Cart";
import Search from "./components/Search";

const App = () => {
  return (
      <Router>
        <Layout className="layout">
          <Header
              style={{
                display: "flex",
                alignItems: "center",
                  justifyContent: "space-evenly",
              }}
          >
              <h1 style={{ color: "white"}}> Магазин продуктов </h1>
              <Search style={{
                  display: "flex",
                  alignItems: "center",
                  justifyContent: "right",
              }}/>
          </Header>
            <Header
                style={{
                    display: "flex",
                    alignItems: "center",
                    justifyContent: "left",
                }}>
                <MainMenu style={{

                }}/>
            </Header>
          <Content style={{ padding: "20px 50px" }}>
            <div className="site-layout-content">
              <Routes>
                  <Route
                      path="/"
                      element={
                          <React.Fragment>
                              <h1>Добро пожаловать! Воспользуйтесь меню для просмотра каталога продуктов!</h1>
                          </React.Fragment>
                      }
                  />
                />
                <Route path="/cart" element={<Cart />} /> {/* Добавлена новая вкладка для корзины */}
                  <Route
                      path="/products"
                      element={
                          <React.Fragment>
                              <ProductsList />
                              <CreateProdutForm />
                          </React.Fragment>
                      }
                  />
              </Routes>
            </div>
          </Content>
        </Layout>
      </Router>
  );
};

export default App;