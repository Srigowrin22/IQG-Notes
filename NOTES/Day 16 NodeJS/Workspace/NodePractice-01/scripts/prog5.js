// Node.js snippet to create a new string with first 3 characters in lowercase,
// and if string length is less than 3, convert all characters to uppercase.

function transformString(str) {
  if (str.length < 3) {
    return str.toUpperCase();
  } else {
    return str.slice(0, 3).toLowerCase() + str.slice(3);
  }
}

// Example usage:
console.log(transformString("HELLO"));   // Output: helLO
console.log(transformString("Hi"));      // Output: HI
console.log(transformString("JavaScript")); // Output: javScript
