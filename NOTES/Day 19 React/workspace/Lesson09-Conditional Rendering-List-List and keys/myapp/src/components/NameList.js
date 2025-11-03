import React from 'react'
import Person from './Person'

function NameList() {
    // const names = ['Rahul', 'Steven', 'Imran'];
    // const nameList = names.map(name => <h2> { name } </h2>)

    const persons = [
        {
            id: 1,
            name: 'Rahul',
            age: 30,
            skill: 'React'
        },
        {
            id: 2,
            name: 'Imran',
            age: 30,
            skill: 'Angular'
        },
        {
            id: 3,
            name: 'Steven',
            age: 30,
            skill: 'OJET'
        }
    ]

    // const personList = persons.map(person => (
    //     <h2>
    //         I am {person.name}. I am {person.age} years Old. I Know {person.skill}
    //     </h2>
    // ))

    const personList = persons.map(person => <Person person = {person}/>)
    return (
        // <div>
        //     <h2> { names[0] } </h2>
        //     <h2> { names[1] } </h2>
        //     <h2> { names[2] } </h2>
        // </div>

        // <div>
        //     {
        //         names.map(name => <h2> { name } </h2>)
        //     }
        // </div>
        // <div> { nameList }</div>
        <div> {personList} </div>
    )
}

export default NameList
