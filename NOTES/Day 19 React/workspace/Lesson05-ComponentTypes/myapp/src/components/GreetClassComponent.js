import React from 'react';

class GreetClassComponent extends React.Component{
    render() {
        return (
            <div>
                Hello, {this.props.name}! Welcome to React!
            </div>
        );
    }
}

export default GreetClassComponent;