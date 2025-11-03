import React, { Component } from 'react'

class UserGreeting extends Component {
    constructor(props) {
        super(props)

        this.state = {
            isLoggedIn: false
        }
    }

    // render() {
    //     if (this.state.isLoggedIn) {
    //         return (
    //             <div>Welcome Rahul!</div>
    //         )
    //     } else {
    //         return (
    //             <div>Welcome Rahul!</div>
    //         )
    //     }
    // }

    // 2nd appraoch
    // render() {
    //     let message
    //     if (this.state.isLoggedIn) {
    //         message = <div><h1>Welcome Rahul</h1></div>
    //     } else {
    //         message = <div><h1>Welcom Imran</h1></div>
    //     }

    //     return <div>{message}</div>
    // }

    // 3rd approach [Ternary condition]
    render() {
        return (
            this.state.isLoggedIn ? <div><h1>Welcom Rahul!</h1></div> : <div><h1>Welcome Imran</h1></div>
        )
    }
}

export default UserGreeting
