import logo from './logo.svg';
import './App.css';
// import Stylesheet from './components/Stylesheet';
// import Inline from './components/Inline';
import './components/appStyles.css'
import styles from './components/appStylesModule.css'

import Form from './components/Form';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        
        {/* <Stylesheet primary = {true}/> */}

        {/* <Inline/> */}

        {/* <h1 className='error'> Error </h1>
        <h1 className={styles.success}>Success</h1> */}

        <Form/>

        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header>
    </div>
  );
}

export default App;
