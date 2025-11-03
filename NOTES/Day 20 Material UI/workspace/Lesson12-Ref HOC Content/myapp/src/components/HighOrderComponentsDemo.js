import React, { Component } from 'react';
 
const PrintHello = ComposedComponent =>
  class extends Component {
    onClick = () => {
      console.log('hello');
    };
 
    render() {
      return <ComposedComponent {...this.props} onClick={this.onClick} />;
    }
  };
 
const FirstComponent = props => (
  <div onClick={props.onClick}>
    Hello, {props.name}! I am first component
  </div>
);
 
const ExtendedComponent = PrintHello(FirstComponent);
 
export default ExtendedComponent;
 