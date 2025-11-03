import React from 'react';
import './App.css';
import Greet from './components/Greet';
import GreetClassComponent from './components/GreetClassComponent';
import GreetFunctionalComp from './components/GreetFunctionalComp';
import GreetClassCompWithState from './components/GreetClassCompWithState';
function App() {
  return (
    <div className="App">
      <Greet />
      <GreetClassComponent name="Buzz" />
      <GreetFunctionalComp name = "Murughan" />
      <GreetClassCompWithState name="MPNN" />
    </div>
  );
}

export default App;
