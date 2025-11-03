import React from "react";

class GreetClassCompWithState extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            toggle: true
        };

        this.onClick = this.onClick.bind(this);
    }

    onClick() {
        this.setState((prevState, props) => ({
            toggle: !prevState.toggle
        }));
    }
    render() {
        return (
            <div onClick={this.onClick}>
                <h2> Hello, {this.props.name}, Welcome to react!</h2>
                <br />
                {/* Toggle is: {this.state.toggle.toString()} */}
                Toggle is: {this.state.toggle ? "ON" : "OFF"}
            </div>
        )
    }
}

export default GreetClassCompWithState