import React, { Component } from 'react'

class LifeCycleB extends Component {
    constructor(props) {
        super(props)

        this.state = {
            name: 'Rahul'
        }
        console.log("Lifecycle B Constructor")
    }

    static getDerivedStateFromProps(props, state) {
        console.log('Lifecycle B getDerivedStateFromProps...')
        return null
    }

    componentDidMount() {
        console.log('Lifecycle B componentDidMount')
    }

    shouldComponentUpdate() {
        console.log('Lifecycle A shouldComponentUpdate')
    }

    getSnapshotBeforeUpdate() {
        console.log('Lifecycle A getSnapshotBeforeUpdate')
        return null;
    }

    render() {
        console.log('Lifecycle B render')
        return (
            <div>LifeCycle B</div>
        )
    }
}

export default LifeCycleB