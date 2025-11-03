const people = [
  { name: "Alice", age: 30 },
  { name: "Bob", age: 25 },
  { name: "Charlie", age: 35 }
];

const totalAge = people.reduce(function(accumulator, person) {
  return accumulator + person.age;
}, 0);

console.log("Total age:", totalAge);