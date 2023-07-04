import { ShoppingCart } from "./components/ShoppingCart";
import {Profile} from "./components/Profile";
import './App.css';
export default function App() {
  return (
      <div className="App">
          <h1>Магазин продуктов</h1>
          <h2>Здравствуйте, </h2>
          <div>
              <Profile/>
          </div>
        <ShoppingCart/>
      </div>
  );
}