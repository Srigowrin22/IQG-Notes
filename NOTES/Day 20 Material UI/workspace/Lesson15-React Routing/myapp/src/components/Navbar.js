import React from 'react'
import { Link } from 'react-router-dom'
import { NavLink } from 'react-router-dom'

export const Navbar = () => {
    return (
        // <nav>
        //     <Link to="/">Home</Link>
        //     <Link to="/about">About</Link>
        // </nav>

        <nav className='primary-nav'>
            <NavLink to="/">Home</NavLink>
            <NavLink to="/about">About</NavLink>
            <NavLink to="/products">Products</NavLink>
        </nav>
    )
}
