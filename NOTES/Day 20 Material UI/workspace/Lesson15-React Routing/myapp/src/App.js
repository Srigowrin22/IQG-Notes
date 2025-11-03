import logo from './logo.svg';
import './App.css';
import { Routes, Route } from 'react-router-dom';
import { Home } from './components/Home';
import { About } from './components/About';
import { Navbar } from './components/Navbar';
import { OrderSummary } from './components/OrderSummary';
import { NoMatch } from './components/NoMatch';
import { Products } from './components/Products';
import { FeaturedProduct } from './components/FeaturedProduct';
import { NewProduct } from './components/NewProduct';

function App() {
  return (

    <>
      <Navbar />
      <Routes>
        <Route path='/' element={<Home />}></Route>
        <Route path='/about' element={<About />}></Route>
        <Route path='/order-summary' element={<OrderSummary />}></Route>

        <Route path='/products' element={<Products />}>
          <Route path='featured' element={<FeaturedProduct />}></Route>
          <Route path='new' element={<NewProduct />}></Route>
        </Route>
        
        <Route path='*' element={<NoMatch />}></Route>
      </Routes>
    </>
  );
}

export default App;
