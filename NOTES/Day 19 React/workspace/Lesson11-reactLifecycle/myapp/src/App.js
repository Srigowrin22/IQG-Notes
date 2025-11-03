import logo from './logo.svg';
import './App.css';
import LifeCycleA from './components/LifeCycleA';
import LifeCycleB from './components/LifeCycleA';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />

        <LifeCycleA />

        {/* <LifeCycleB/> */}

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
