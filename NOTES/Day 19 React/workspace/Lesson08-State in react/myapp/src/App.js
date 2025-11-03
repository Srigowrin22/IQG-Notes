import logo from './logo.svg';
import './App.css';
// import Message from './components/Message';
// import Counter from './components/Counter';

// import FunctionClick from './components/FunctionClick'
// import ClassClick from './components/ClassClick';
// import EventBind from './components/EventBind';
import ManagedControlComp from './components/ManagedControlComp';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />

        {/* <Message /> */}
        {/* <Counter/> */}

        {/* <FunctionClick/> */}

        {/* <ClassClick/> */}

        {/* <EventBind /> */}
        <ManagedControlComp/>
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
