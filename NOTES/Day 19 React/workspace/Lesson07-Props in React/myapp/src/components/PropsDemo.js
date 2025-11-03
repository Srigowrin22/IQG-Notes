// import React from 'react';

// class Parent extends React.Component {

//     doSomthing() {
//         console.log("Parent Component");
//     }

//     render() {
//         return <div>
//             <Child text="This is the child 1" title="Title 1"
//                 onClick={this.doSomthing} />

//             <Child text="This is the child 2" title="Title 2"
//                 onClick={this.doSomthing} />
//         </div>
//     }
// }

// class Child extends React.Component {
//     render() {
//         return <div onClick={this.props.onClickEvent}>
//             <h1> {this.props.title} </h1>
//             <h2> {this.props.text} </h2>
//         </div>
//     }
// }

// export default Parent


import React from "react";
class Parent extends React.Component{
    doSomething(){
        console.log("Parent Component");
    }
    render(){
        return <div>
            <Child text = "This is Child text" title = "title1"
            onClickEvent = {this.doSomething} />
        </div>
    }
}
class Child extends React.Component{
    render(){
        return <div onClick={this.props.onClickEvent}>
            <h1>{this.props.title}</h1>
            <h1>{this.props.text}</h1>
        </div>
    }
}
export default Parent
 