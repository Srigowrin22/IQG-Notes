import React, { Component } from 'react'
import LifeCycleB from './LifeCycleB'

class LifeCycleA extends Component {
    constructor(props) {
        super(props)

        this.state = {
            name: 'Rahul'
        }
        console.log("Lifecycle A Constructor")
    }

    static getDerivedStateFromProps(props, state) {
        console.log('Lifecycle A getDerivedStateFromProps...')
        return null
    }

    componentDidMount() {
        console.log('Lifecycle A componentDidMount')
    }

    shouldComponentUpdate() {
        console.log('Lifecycle A shouldComponentUpdate')
    }

    getSnapshotBeforeUpdate() {
        console.log('Lifecycle A getSnapshotBeforeUpdate')
        return null;
    }

    changeState = () => {
        this.setState({
            name: 'World of react'
        })
    }

    render() {
        console.log('Lifecycle A render')
        return (
            <div>
                LifeCycle A
                <button onClick={this.changeState}>Change State</button>
                <LifeCycleB />
            </div>

        )
    }
}

export default LifeCycleA