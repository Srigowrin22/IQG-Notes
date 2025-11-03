import logo from './logo.svg';
import './App.css';
// import { Greet } from './components/Greet';
import Welcome from './components/Welcome';
// import Parent from './components/PropsDemo';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        {/* <Greet/> */}

        {/* <Greet name = "Rahul" heroName = "Batman"/>
          <p>This is Childern props</p>
        <Greet name = "Steven" heroName = "SuperMan"/>
          <button>Action</button>
        <Greet name = "Imran" heroName = "SpiderMan"/> */}

        <Welcome name = "Imran" heroName = "SpiderMan"/>

        {/* <Parent/> */}

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
