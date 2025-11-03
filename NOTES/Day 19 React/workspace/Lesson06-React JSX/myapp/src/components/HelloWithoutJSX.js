import React from "react";

const HelloWithoutJSX = () => {
    // return React.createElement('div', null, React.createElement('h1',null,'Hello React without JSX!'));

    //Passing properties to the element [using secondargument in createElement]
    return React.createElement(
        'div',
        {id: 'helloDiv', className : 'hello'},
        React.createElement('h1', null, 'Hello React without JSX!'));
}

export default HelloWithoutJSX