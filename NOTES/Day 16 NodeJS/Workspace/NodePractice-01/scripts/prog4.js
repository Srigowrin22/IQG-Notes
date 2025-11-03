function checkFifty(num1, num2) {
  return num1 === 50 || num2 === 50 || (num1 + num2) === 50;
}

console.log(checkFifty(50, 10)); 
console.log(checkFifty(20, 30));
console.log(checkFifty(20, 25));