function isHappyNumber(num) {
  let seen = new Set();
  while (num !== 1 && !seen.has(num)) {
    seen.add(num);
    num = num.toString().split('').reduce((sum, digit) => {
      return sum + Math.pow(Number(digit), 2);
    }, 0);
  }
  return num === 1;
}

let count = 0;
let number = 1;
let happyNumbers = [];

while (count < 5) {
  if (isHappyNumber(number)) {
    happyNumbers.push(number);
    count++;
  }
  number++;
}

console.log("First 5 happy numbers:", happyNumbers);
