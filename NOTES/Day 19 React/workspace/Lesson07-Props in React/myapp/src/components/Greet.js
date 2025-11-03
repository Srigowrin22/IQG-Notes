import React from "react";

// export const Greet = () => <h1> Hello React! Happy programming!</h1>

export const Greet = (props) => {
    console.log(props);
    return (
    <div>

        <h1>
            Welcome {props.name} A.K.A {props.heroName} Hello react {props.children}
        </h1>
        
    </div>)
}
