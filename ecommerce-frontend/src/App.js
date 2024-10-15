import React from 'react';
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import Login from './components/Login';
import Register from './components/Register';
import ProductList from './components/ProductList';
import ProductDetail from './components/ProductDetails';
import Cart from './components/Cart';
import Checkout from './components/Checkout';
import './App.css';

function App() {
    return (
        <div className="App">
            <Router>
                <Routes>
                    <Route path="/users/login" element={<Login/>}/>
                    <Route path="/users/register" element={<Register/>}/>
                    <Route path="/products/all" element={<ProductList/>}/>
                    <Route path="/products/:id" element={<ProductDetail/>}/>
                    <Route path="/cart" element={<Cart/>}/>
                    <Route path="/checkout" element={<Checkout/>}/>
                </Routes>
            </Router>
        </div>
    );
}

export default App;