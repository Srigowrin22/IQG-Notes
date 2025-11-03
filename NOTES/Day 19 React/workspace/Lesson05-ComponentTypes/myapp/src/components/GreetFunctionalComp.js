import React from 'react';
import PropTypes from 'prop-types';
 
const GreetFunctionalComp = props => (
    <div>
        Hello, {props.name} !, Welcome to React !
    </div>
);
 
//Validation
GreetFunctionalComp.propTypes = {
    name: PropTypes.string.isRequired
}
 
export default GreetFunctionalComp;