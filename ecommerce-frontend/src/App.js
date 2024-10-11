import React from 'react';
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import ProductList from './components/ProductList';
import ProductDetail from './components/ProductDetails';
import './App.css';

function App() {
    return (
        <div className="App">
            <Router>
                <Routes>
                    <Route path="/products/all" element={<ProductList/>}/>
                    <Route path="/products/:id" element={<ProductDetail/>}/>
                </Routes>
            </Router>
        </div>
    );
}

export default App;