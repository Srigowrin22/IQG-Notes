import React from 'react'
import './myStyle.css'

function Stylesheet(props) {
    let styleClassName = props.primary? 'primary' : ''
  return (
    <div>
        <h1 className={ `${styleClassName} font-x1` }>Stylesheet Welcome</h1>
    </div>
  )
}

export default Stylesheet