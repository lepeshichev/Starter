import React from "react";
import { Layout, Row, Col } from "antd";
import { Content, Header } from "antd/es/layout/layout";
import {BrowserRouter as Router, Link, Route, Routes} from "react-router-dom";
import MainMenu from "./components/MainMenu";
import ProductsList from "./components/ProductsList";
import CreateProductForm from "./components/CreateProductForm";
import Cart from "./components/Cart";
import Search from "./components/Search";
import {useDispatch, useSelector} from "react-redux";
import {logout} from "./slices/AuthSlice";
import RegistrationPage from "./components/RegistrationPage";
import LoginPage from "./components/LoginPage";

const App = () => {
    //const {user: currentUser} = useSelector((state) => state.auth);
    const dispatch = useDispatch();

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
                              <CreateProductForm />
                          </React.Fragment>
                      }
                  />
                  <Route path="/register" element={<RegistrationPage/>}/>
                  <Route path="/login" element={<LoginPage/>}/>
              </Routes>
            </div>
          </Content>
        </Layout>
      </Router>
  );
};

export default App;