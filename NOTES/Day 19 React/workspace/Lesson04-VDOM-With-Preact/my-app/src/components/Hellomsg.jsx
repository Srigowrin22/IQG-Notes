import{h, render, Component} from 'preact';
class Hellomsg extends Component{
    render(){
        return <h1> Welcome preact </h1>
    }
}

render(<Hellomsg/>, document.getElementById("helloDiv"));